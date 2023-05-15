package com.kisaan.jai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryIdDTO {

    @NotNull(message = "Category id cann't be empty")
    private Long categoryId;
}
