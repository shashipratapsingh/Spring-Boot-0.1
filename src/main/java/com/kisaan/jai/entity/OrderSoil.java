package com.kisaan.jai.entity;

import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderSoil extends CreateOrUpdateDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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
