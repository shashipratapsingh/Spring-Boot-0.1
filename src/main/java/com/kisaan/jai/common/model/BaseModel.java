package com.kisaan.jai.common.model;

import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseModel extends CreateOrUpdateDetails{


	@NotBlank(message = "can not be empty")
	private String name;

	private String description;

	private String image;
}
