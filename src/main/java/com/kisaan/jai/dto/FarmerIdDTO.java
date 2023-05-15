package com.kisaan.jai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class FarmerIdDTO {

    @NotNull(message = "Farmer id can't be empty")
    private Long farmerId;


}