package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.BaseModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "name", "description", "price"})
public class ProductDTO extends BaseModel {

    private Long id;
    private float price;
    @NotBlank(message = "can not be empty")
    private String shortDesc;
    private String discount;
    private Long subCategoryId;
    private List<CropDTO> crops;
    private Long categoryId;

    private List<ProductFeatureDTO> longDesc;
}