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
public class PostRes {

    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String summary;

    private String coverImagePath;

    private Long createdDate;

    private Long lastModifiedDate;

    private Long numberOfVotes;

    private Long numberOfComments;

    private Long numberOfFavorites;

    private PostType postType;

    @JsonProperty("tags")
    private List<TagRes> tagsRes;

    @JsonProperty("user")
    private UserRes userRes;
}
