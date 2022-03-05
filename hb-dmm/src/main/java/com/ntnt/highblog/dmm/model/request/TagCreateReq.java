package com.ntnt.highblog.dmm.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagCreateReq {

    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$")
    private String name;
}
