package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "orderAmount", "orderDate"})
public class ProductOrderDTO extends CreateOrUpdateDetails{

	private Long id;

//	@NotBlank(message = "please enter the order amount")
	@NotNull(message = "amount can not be empty")
	private double orderAmount;

	@NotNull(message = "Farmer id can't be empty")
	private Long farmerId;

	private ZonedDateTime orderDate;
	
	private List<OrderProductDTO> orderProducts;
}
