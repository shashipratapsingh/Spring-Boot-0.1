package com.kisaan.jai.controller;

import com.kisaan.jai.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController {

    public ImageService imageService;

    @GetMapping("/{path}/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("path") String folderName, @PathVariable("fileName") String fileName) {
        byte[] image = new byte[0];
        try {
            image = imageService.getImage(folderName, fileName);
        } catch (IOException e) {
            log.error("Exception while retrieving image : {}",e.getMessage(), e);
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
