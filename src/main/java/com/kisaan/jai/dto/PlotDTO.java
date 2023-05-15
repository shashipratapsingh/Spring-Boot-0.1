package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"createdBy", "modifiedBy", "farmer", "isActive"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "ownerName", "otherDetails"})
public class PlotDTO extends CreateOrUpdateDetails {

    private Long id;
    @NotBlank(message = " owner name can not be blank")
    private String ownerName;
    @NotBlank(message = "can not be empty")
    private String otherDetails;
    @NotBlank(message = "can not be empty")
    private String address;

    private String plotInfo;

    @JsonIgnoreProperties(value = {"plots", "createdDate", "modifiedDate"})
    private VillageDTO village;

    @JsonIgnoreProperties(value = {"products", "createdDate", "modifiedDate"})
    private List<CropDTO> crops;

    private FarmerDTO farmer;
}
