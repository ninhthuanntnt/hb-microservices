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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hb_posts_tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostTag
        extends AbstractAuditingColumns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "post_id")
    private Long postId;

    @NotNull
    @Column(name = "tag_id")
    private Long tagId;

    @Transient
    private String name;

    public PostTag(final Long tagId, final String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public PostTag(final Long tagId, final Long postId, final String name) {
        this.tagId = tagId;
        this.postId = postId;
        this.name = name;
    }
}
