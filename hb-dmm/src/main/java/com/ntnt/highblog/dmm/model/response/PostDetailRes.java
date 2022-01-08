package com.ntnt.highblog.dmm.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDetailRes {
    private String title;

    private String content;

    private String coverImagePath;

    private Long createdDate;

    private Long lastModifiedDate;

    private Long numberOfVotes;

    private Long numberOfComments;

    private Long numberOfFavorites;

    private Boolean addedToFavorite;

    @JsonProperty("tags")
    private List<TagRes> tagsRes;

    @JsonProperty("vote")
    private PostVoteRes postVoteRes;

    @JsonProperty("user")
    private UserRes userRes;
}
