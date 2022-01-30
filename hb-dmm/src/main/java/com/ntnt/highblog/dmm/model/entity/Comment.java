package com.ntnt.highblog.dmm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.List;

@Entity
@Table(name = "hb_comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment
        extends AbstractAuditingColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "number_of_votes")
    private Long numberOfVotes = 0L;

    @Transient
    private Long numberOfChildComments;

    @Transient
    private List<Comment> childComments;

    @Transient
    private User user;

    public Comment(Long id, Long parentId, Long userId, Long postId, String content, Long numberOfChildComments) {
        this.id = id;
        this.parentId = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.numberOfChildComments = numberOfChildComments;
    }
}
