package com.ntnt.highblog.notification.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ntnt.highblog.notification.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {

    private Long id;

    private String nickName;

    private String firstName;

    private String lastName;

    private String imagePath;

    private GenderType genderType;

    private String backgroundPath;

    private Boolean followed;

    private Boolean notified;

    private Long createdDate;

    private String bio;

    private String websiteUrl;

    private String location;

}
