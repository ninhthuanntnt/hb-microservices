package com.ntnt.highblog.notification.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BasePaginationReq {

    @Builder.Default
    private Integer page = 1;

    @Builder.Default
    @JsonProperty("page_size")
    private Integer pageSize = 10;

    private String[] sorts;
}
