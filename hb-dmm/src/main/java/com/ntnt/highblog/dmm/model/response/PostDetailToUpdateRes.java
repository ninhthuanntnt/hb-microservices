package com.ntnt.highblog.dmm.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntnt.highblog.dmm.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDetailToUpdateRes {
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

    @JsonProperty("tags")
    private List<TagRes> tagsRes;
}
