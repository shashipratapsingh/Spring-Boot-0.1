package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "name", "description"})
public class DroneOrderDTO extends CreateOrUpdateDetails{

	private Long id;

	private Long farmerId;

	private String farmerName;
	private String farmerAddress;
	private String fatherName;

	private String address;

	private String mobile;

	private String surveyId;

	private String cropName;

	private String areaCovered;

	private String storageOfCrop;
	private String productType;
	private String productName;
	private String doseOfProduct;

	private Date sprayDate;

	private String droneOperatorDetail;
}