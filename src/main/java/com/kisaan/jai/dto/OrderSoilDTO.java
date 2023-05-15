package com.kisaan.jai.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "name", "description"})
public class OrderSoilDTO extends CreateOrUpdateDetails{

	private Long id;

	private Long farmerId;

	private String farmerName;

	private String fatherName;

	private String mobile;

	private Long districtId;

	private Long tahsilId;

	private Long villageId;
	private Long stateId;

	private String longitude;

	private String latitude;

	private String surveyId;

	private String nextCrop;

	private String jksName;

	private String jksMobile;

	private String jksTerritory;
}
