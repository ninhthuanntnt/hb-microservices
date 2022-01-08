package com.ntnt.highblog.dmm.model.entity.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("Post")
public class PostNode {
    @Id
    private Long id;

    @Property("title")
    private String title;

    @Relationship(type = "HAS_TAG", direction = Relationship.Direction.OUTGOING)
    private List<TagNode> tagNodes;
}
