package com.ntnt.highblog.dmm.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntnt.highblog.dmm.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateReq {

    @NotNull
    private Long categoryId;

    @NotNull
    @NotEmpty
    private String title;

    private String summary;

    @NotNull
    @NotEmpty
    private String content;

    private String coverImagePath;

    @NotNull
    private PostType postType;

    @Builder.Default
    @JsonProperty("tags")
    private List<TagCreateReq> tagCreateReqs = Collections.emptyList();
}
