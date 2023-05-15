package com.kisaan.jai.entity;

import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneOrder extends CreateOrUpdateDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long farmerId;

	private String farmerName;

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
