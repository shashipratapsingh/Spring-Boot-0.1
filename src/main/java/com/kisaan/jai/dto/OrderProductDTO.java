package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderProductDTO {

    private int quantity;

    private double price;

    @JsonIgnoreProperties(value = {"categoryId", "subCategoryId", "crops", "productFeature"})
    private ProductDTO product;
}
