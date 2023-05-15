package com.kisaan.jai.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideosService {

	String addVideo(MultipartFile video, String name)throws IOException;
	
	String getVideo(String fileName) throws IOException;
}