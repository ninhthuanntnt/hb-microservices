package com.ntnt.highblog.dmm.model.request;

import com.ntnt.highblog.dmm.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateReq {
// TODO: Add validate for necessary fields

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9_]")
    private String nickName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private GenderType genderType;

    private String websiteUrl;

    private String location;

    private String bio;

}