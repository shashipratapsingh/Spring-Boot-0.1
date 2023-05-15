package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "description"})
public class ProductFeatureDTO {

	private Long id;

	@NotBlank(message = "can not be empty")
	private String description;

	private ProductDTO product;
}
