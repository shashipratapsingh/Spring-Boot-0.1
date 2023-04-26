package com.Zee5blog.Zee5.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Zee5blog.Zee5.services.FileService;


@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		 //File Name
		
		String name=file.getOriginalFilename();
		
		//random name generate file
		
		String randomID=UUID.randomUUID().toString();
		String filename1=randomID.concat(name.substring(name.lastIndexOf(".")));
		//full path
		
		String filepath=path+File.separator+filename1;
		//create folder if not created
		
		File f=new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		return filename1;
	}

	@Override
	public InputStream getResouce(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullPath); 
		return is;
	}

}
