package com.kisaan.jai.entity;

import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Farmer extends CreateOrUpdateDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	private int age;
	private String address;
	private String mobile;

	@OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "farmer")
	private List<Plot> plots;

	@OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "farmer")
	private List<CropPlotMapping> crop;
}
