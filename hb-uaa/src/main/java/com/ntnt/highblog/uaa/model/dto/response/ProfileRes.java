package com.ntnt.highblog.uaa.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ntnt.highblog.uaa.enums.RoleType;
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
public class ProfileRes {

    private Long id;

    private String nickName;

    private String firstName;

    private String lastName;

    private String imagePath;

    private List<RoleType> roleTypes;
}
