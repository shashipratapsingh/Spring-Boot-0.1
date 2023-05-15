package com.kisaan.jai.dto;


import com.kisaan.jai.common.model.CreateOrUpdateDetails;
import lombok.Data;

@Data
public class VideosDTO extends CreateOrUpdateDetails{

	private Long id;
	private String name;
	private String fileName;
	private String imagePath;
	
}
