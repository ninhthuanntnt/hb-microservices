package com.ntnt.highblog.dmm.neo4j.repository;

import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PostNodeRepository extends Neo4jRepository<PostNode, Long> {
    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)"
        + " MATCH (otherPost:Post)-[:HAS_TAG]->(otherPostTag:Tag)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + " OPTIONAL MATCH (user)-[readOtherPost:READ]->(otherPost)"
        + " WITH post, otherPost, CASE WHEN readOtherPost.count IS NULL THEN 0 ELSE readOtherPost.count END AS readCount,"
        + "       COLLECT(DISTINCT tag.name) AS postTagNames,           "
        + "       COLLECT(DISTINCT otherPostTag.name) AS otherPostTagNames"
        + " WITH post, postTagNames, otherPost, otherPostTagNames, readCount"
        + " WITH otherPost, postTagNames, otherPostTagNames, readCount,"
        + "       apoc.coll.sum(gds.alpha.ml.oneHotEncoding(postTagNames, otherPostTagNames)) / SIZE(postTagNames+[x IN otherPostTagNames WHERE NOT x IN postTagNames]) AS jaccard,"
        + "       REDUCE(sim=0.0, ptName IN postTagNames "
        + "             | sim+REDUCE(subSim=0.0, optName IN otherPostTagNames "
        + "                         | subSim+apoc.text.jaroWinklerDistance(ptName, optName))) AS similarity"
        + " ORDER BY "
        + "       readCount ASC,"
        + "       jaccard DESC,"
        + "       similarity DESC,"
        + "       otherPost.numberOfVotes + 2*otherPost.numberOfFavorites DESC, otherPost.id DESC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchForNewsfeed(Long userId, Long skip, Integer limit);

    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)"
        + " MATCH (otherPost:Post)-[:HAS_TAG]->(otherPostTag:Tag)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countForNewsfeed(Long userId);

    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " OPTIONAL MATCH (user)-[readOtherPost:READ]->(otherPost)"
        + " WITH post, otherPost, readOtherPost.count AS readCount, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, readCount, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, readCount, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " WITH post, otherPost, readCount, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag "
        + " WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard"
        + " ORDER BY readCount ASC, jaccard DESC, tagIntersection DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchRecommendPostsByUserId(Long userId, Long skip, Integer limit);

    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countRecommendPostsByUserId(Long userId);

    @Query("MATCH (user:User {id: $userId})"
        + " MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + " OPTIONAL MATCH (user)-[readOtherPost:READ]->(otherPost)"
        + " WITH post, otherPost, readOtherPost.count AS readCount, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, readCount, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, readCount, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " WITH post, otherPost, readCount, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag "
        + " WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard"
        + " ORDER BY readCount ASC, jaccard DESC, tagIntersection DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchRecommendPostsByIdAndUserId(Long id, Long userId, Long skip, Integer limit);

    @Query("MATCH (user:User {id: $userId})"
        + " MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countRecommendPostsByIdAndUserId(Long id, Long userId);

    @Query("MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WITH post, otherPost, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " WITH post, otherPost, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag "
        + " WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard"
        + " ORDER BY jaccard DESC, tagIntersection DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchRecommendPostsById(Long id, Long skip, Integer limit);

    @Query("MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WITH post, otherPost, COUNT(DISTINCT tag) AS tagIntersection"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countRecommendPostsById(Long id);
}
