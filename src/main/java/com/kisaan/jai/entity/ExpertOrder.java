package com.kisaan.jai.entity;

import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertOrder extends CreateOrUpdateDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(columnDefinition="Decimal(10,2) default '100.00'")
	private double price;

	private Long farmerId;

	private Long expertId;
}
