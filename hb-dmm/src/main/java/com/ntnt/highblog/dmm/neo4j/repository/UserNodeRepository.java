package com.ntnt.highblog.dmm.neo4j.repository;

import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserNodeRepository
    extends Neo4jRepository<UserNode, Long> {

    @Query("MATCH (author:User {id: $id})-[:IS_AUTHOR]->(post:Post)<-[:READ]-(reader:User)"
        + " WITH author, COLLECT(DISTINCT reader.id) AS readerIds"
        + " MATCH (relatedUser:User)-[:IS_AUTHOR]->(otherPost:Post)<-[:READ]-(relatedReader:User)"
        + " WHERE relatedUser <> author "
        + "     AND ($currentUserId IS NULL OR NOT EXISTS ((:User {id: $currentUserId})-[:FOLLOWS]->(relatedUser)))"
        + " WITH author, readerIds, relatedUser, COLLECT(DISTINCT relatedReader.id) AS relatedReaderIds"
        + " WITH author, relatedUser, apoc.coll.sum(gds.alpha.ml.oneHotEncoding(readerIds, relatedReaderIds)) / SIZE(readerIds) AS sim"
        + " ORDER BY sim DESC, relatedUser.id ASC"
        + " RETURN relatedUser"
        + " SKIP $skip LIMIT $limit")
    List<UserNode> fetchRelatedUsersById(Long id, Long currentUserId, Long skip, Integer limit);

    @Query("MATCH (author:User {id: $id})-[:IS_AUTHOR]->(post:Post)<-[:READ]-(reader:User)"
        + " WITH author, COLLECT(DISTINCT reader.id) AS readerIds"
        + " MATCH (relatedUser:User)-[:IS_AUTHOR]->(otherPost:Post)<-[:READ]-(relatedReader:User)"
        + " WHERE relatedUser <> author "
        + "     AND ($currentUserId IS NULL OR NOT EXISTS ((:User {id: $currentUserId})-[:FOLLOWS]->(relatedReader)))"
        + " RETURN COUNT(DISTINCT relatedReader)")
    int countRelatedUserById(Long id, Long currentUserId);

    @Query("MATCH (follower:User {id: $followerId})-[f:FOLLOWS]->(followedUser:User {id: $followedUserId})"
        + " RETURN COUNT(f) > 0")
    boolean existsFollowRelationShip(Long followerId, Long followedUserId);

    @Query("MATCH (follower:User {id: $followerId})-[f:FOLLOWS]->(followedUser:User {id: $followedUserId})"
        + " DELETE f")
    void deleteFollowsRelationship(Long followerId, Long followedUserId);

    @Query("MATCH (follower:User {id: $followerId})"
        + " MATCH (followedUser:User {id: $followedUserId})"
        + " CREATE (follower)-[:FOLLOWS]->(followedUser)")
    void createFollowsRelationship(Long followerId, Long followedUserId);

}
