package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "name", "mobile"})
public class FarmerDTO extends MobileNumberDTO{
	
	private Long id;

	@NotBlank(message = "Name cann't be empty")
	private String name;

	@Min(18)
	@Max(100)
	private int age;

	@NotBlank(message = "Address can't empty")
	private String address;

	List<PlotDTO> plots;

	private Long villageId;

	private Long stateId;

	private Long districtId;

	private Long tahsilId;

	private Long plotId;

	private String plotInfo;

	private List<CropDTO> crops;
}