package com.ntnt.highblog.dmm.repository.neo4j;

import com.ntnt.highblog.dmm.model.entity.neo4j.PostNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface PostNodeRepository extends Neo4jRepository<PostNode, Long> {
    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:READ]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, tag, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag "
        + " WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard"
        + " ORDER BY jaccard DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchRecommendPostsByUserId(Long userId, Long skip, Integer limit);

    @Query("MATCH (user:User {id: $userId})-[:READ]->(post:Post)-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:READ]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, tag, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countRecommendPostsByUserId(Long userId);

    @Query("MATCH (user:User {id: $userId})"
        + " MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:READ]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, tag, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, tagOfOtherPostId, tagOfPostId+[x IN tagOfOtherPostId WHERE NOT x IN tagOfPostId] AS unionTag "
        + " WITH otherPost, ((1.0*tagIntersection)/SIZE(unionTag)) AS jaccard"
        + " ORDER BY jaccard DESC, otherPost.numberOfVotes DESC, otherPost.numberOfFavorites DESC, otherPost.title DESC, id(otherPost) ASC"
        + " RETURN DISTINCT otherPost"
        + " SKIP $skip LIMIT $limit")
    List<PostNode> fetchRecommendPostsByIdAndUserId(Long id, Long userId, Long skip, Integer limit);

    @Query("MATCH (user:User {id: $userId})"
        + " MATCH (post:Post {id: $id})-[:HAS_TAG]->(tag:Tag)<-[:HAS_TAG]-(otherPost:Post)"
        + " WHERE NOT EXISTS ((user)-[:READ]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(otherPost))"
        + "       AND NOT EXISTS ((user)-[:IS_AUTHOR]->(post))"
        + " WITH post, otherPost, tag, COUNT(DISTINCT tag) AS tagIntersection"
        + " MATCH (post)-[:HAS_TAG]->(tagOfPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, COLLECT(DISTINCT tagOfPost.id) AS tagOfPostId"
        + " MATCH (otherPost)-[:HAS_TAG]->(tagOfOtherPost:Tag)"
        + " WITH post, otherPost, tag, tagIntersection, tagOfPostId, COLLECT(DISTINCT tagOfOtherPost.id) AS tagOfOtherPostId"
        + " RETURN COUNT(DISTINCT otherPost)")
    int countRecommendPostsByIdAndUserId(Long id, Long userId);
}
