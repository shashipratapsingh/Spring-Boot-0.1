package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "name", "description"})
public class ExpertOrderDTO extends CreateOrUpdateDetails{

	private Long id;

	private Long farmerId;

	private Long expertId;
}