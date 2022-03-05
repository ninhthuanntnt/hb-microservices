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
@Node("User")
public class UserNode {
    @Id
    private Long id;

    @Property("nickName")
    private String nickName;

    @Relationship(type = "IS_AUTHOR", direction = Relationship.Direction.OUTGOING)
    private List<PostNode> authorPostNodes;

    @Relationship(type = "READ", direction = Relationship.Direction.OUTGOING)
    private List<PostNode> readPostNodes;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private List<UserNode> followedUser;
}
