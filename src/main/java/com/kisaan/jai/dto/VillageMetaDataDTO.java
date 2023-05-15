package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VillageMetaDataDTO {

    @NotNull(message = "Can't be empty")
    private Long stateId;

    @NotNull(message = "Can't be empty")
    private Long districtId;

    @NotNull(message = "Can't be empty")
    private Long tahsilId;
}
