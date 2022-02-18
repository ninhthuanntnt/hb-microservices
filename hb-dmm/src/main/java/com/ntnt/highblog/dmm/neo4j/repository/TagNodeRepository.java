package com.ntnt.highblog.dmm.neo4j.repository;

import com.ntnt.highblog.dmm.model.entity.neo4j.TagNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagNodeRepository extends Neo4jRepository<TagNode, Long> {
}
