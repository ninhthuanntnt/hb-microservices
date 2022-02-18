package com.ntnt.highblog.dmm.neo4j.repository;

import com.ntnt.highblog.dmm.model.entity.neo4j.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserNodeRepository extends Neo4jRepository<UserNode, Long> {
}
