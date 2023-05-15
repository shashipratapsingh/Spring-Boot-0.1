package com.kisaan.jai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductIdDTO {
    @NotNull(message = "cann't be empty")
    private Long categoryId;

    private Long productId;
    @NotNull(message = "cann't be empty")
    private Long subCategoryId;
    private String name;
}
