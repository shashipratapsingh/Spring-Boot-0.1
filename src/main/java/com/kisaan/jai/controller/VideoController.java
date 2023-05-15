package com.kisaan.jai.controller;

import com.kisaan.jai.service.VideosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {

	@Autowired
	private VideosService videoService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadVideo(@RequestParam MultipartFile video, @RequestParam String name)throws IOException{
		String addVideo=null;
		log.info("inside the controller upload video  method");

		if(video.getContentType()=="image/png" || video.getContentType()=="image/jpeg" || !video.isEmpty())
		{
			log.info("into the coontroller class");
			addVideo = this.videoService.addVideo(video, name);
			return ResponseEntity.status(HttpStatus.CREATED).body("video is uploaded successfully  !!");
		}
		return ResponseEntity.badRequest().body("get some error in image uplaoding file ");
	}
	
	
//	@GetMapping("/getFile")
//	public ResponseEntity<byte[]> getVideo(@RequestParam String fileName){
//		log.info("get it in controller  "+fileName);
//		String video = this.videoService.getVideo(fileName);
//		if(video=="got it") {
//		return ResponseEntity.ok body(video);
//		}
//		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG_VALUE).body(video);

}