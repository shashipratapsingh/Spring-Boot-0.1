package com.kisaan.jai.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
public class CreateOrUpdateDetails {

	@CreationTimestamp
	private ZonedDateTime createdDate;

	@UpdateTimestamp
	private ZonedDateTime modifiedDate;
	
	@CreatedBy
	private String createdBy = "ADMIN";
	
	@LastModifiedBy
	private String modifiedBy = "ADMIN";

	@Column(columnDefinition = "boolean default true")
	private Boolean isActive = true;
}