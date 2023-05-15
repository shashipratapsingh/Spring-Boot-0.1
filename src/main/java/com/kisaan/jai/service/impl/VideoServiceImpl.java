package com.kisaan.jai.service.impl;

import com.kisaan.jai.entity.Videos;
import com.kisaan.jai.exception.NoSuchElementExistException;
import com.kisaan.jai.repository.VideosRepository;
import com.kisaan.jai.service.VideosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideosService{

	private final VideosRepository videosRepository;

	private final ModelMapper modelMapper;

	@Value("${file.path.video}")
	private String videoFilePath;

	
	@Override
	public String addVideo(MultipartFile video, String name) throws IOException {
		log.info("into the service class");
		
		String filePath = new StringBuilder(videoFilePath)
				.append(video.getOriginalFilename())
				.toString();
		
		Videos save = videosRepository.save(Videos.builder()
						.fileName(video.getOriginalFilename())
						.imagePath(videoFilePath)
						.name(name).build());

				video.transferTo(new File(filePath));
				if(save!=null) {
					return "file uploaded successfully : ";
				} 
				return "please upload file all documents in pdf file formate ";
	}

	@Override
	public String getVideo(String fileName) throws IOException {
		Videos video = videosRepository.findByName(fileName)
				.orElseThrow(() -> new NoSuchElementExistException("Video file not found"));

		return video.getFileName();
	}
}
