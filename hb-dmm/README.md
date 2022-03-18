## Fetch related post
*Recommend by jaccard index*
``` mysql
MATCH (user:User {id: 977})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)
WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))
      AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))
OPTIONAL MATCH (user)-[readOtherPost:READ]->(otherPost)
WITH post, otherPost, COUNT(DISTINCT readOtherPost) AS read, COUNT(DISTINCT tag) AS tagIntersection
MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)
WITH post, otherPost, read, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId
MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)
WITH post, otherPost, read, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId
WITH post, otherPost, read, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag 
WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard
ORDER BY read ASC, jaccard DESC, tagIntersection DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC
RETURN DISTINCT otherPost
SKIP 0 LIMIT 20
```

## Fetch for newsfeed
*Recommend by jaccard index and total similarity of each tag*
``` mysql
MATCH (user:User {id: 977})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)
MATCH (otherPost:Post)-[:HAS_TAG]->(otherPostTag:Tag)
WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(post))
      AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))
      //AND NOT EXISTS ((otherPost)-[:HAS_TAG]->(:Tag)<-[:HAS_TAG]-(:Post)<-[:READ]-(user))
OPTIONAL MATCH (user)-[readOtherPost:READ]->(otherPost)
WITH post, otherPost, CASE WHEN readOtherPost.count IS NULL THEN 0 ELSE readOtherPost.count END AS readCount,
      COLLECT(DISTINCT tag.name) AS postTagNames,           
      COLLECT(DISTINCT otherPostTag.name) AS otherPostTagNames
WITH post, postTagNames, otherPost, otherPostTagNames, readCount
WITH otherPost, postTagNames, otherPostTagNames, readCount,
      apoc.coll.sum(gds.alpha.ml.oneHotEncoding(postTagNames, otherPostTagNames)) / SIZE(postTagNames+[x IN otherPostTagNames WHERE NOT x IN postTagNames]) AS jaccard,
      REDUCE(sim=0.0, ptName IN postTagNames 
            | sim+REDUCE(subSim=0.0, optName IN otherPostTagNames 
                        | subSim+apoc.text.sorensenDiceSimilarity(ptName, optName))) AS similarity
ORDER BY 
      readCount ASC,
      CASE WHEN jaccard = 0 THEN similarity ELSE jaccard END DESC, 
      otherPost.numberOfVotes + 2*otherPost.numberOfFavorites DESC, otherPost.id DESC
RETURN otherPost.id, readCount, postTagNames, otherPostTagNames, jaccard, similarity
SKIP 0 LIMIT 20
```

## Fetch related user
``` mysql
MATCH (author:User {id: $id})-[:IS_AUTHOR]->(post:Post)<-[:READ]-(reader:User)
WITH author, COLLECT(DISTINCT reader.id) AS readerIds
MATCH (relatedUser:User)-[:IS_AUTHOR]->(otherPost:Post)<-[:READ]-(relatedReader:User)
WHERE relatedUser <> author 
    AND ($currentUserId IS NULL OR NOT EXISTS ((:User {id: $currentUserId})-[:FOLLOWS]->(relatedUser)))
WITH author, readerIds, relatedUser, COLLECT(DISTINCT relatedReader.id) AS relatedReaderIds
WITH author, relatedUser, apoc.coll.sum(gds.alpha.ml.oneHotEncoding(readerIds, relatedReaderIds)) / SIZE(readerIds) AS sim
ORDER BY sim DESC, relatedUser.id ASC
RETURN relatedUser
SKIP $skip LIMIT $limit
```