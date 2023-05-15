package com.kisaan.jai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CropIdDTO {

    @NotNull(message = "Crop id can't be empty")
    private Long cropId;

    private Long categoryId;

    private Long subCategoryId;
    private String name;
}