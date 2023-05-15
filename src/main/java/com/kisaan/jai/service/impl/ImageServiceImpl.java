package com.kisaan.jai.service.impl;

import com.kisaan.jai.service.ImageService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Value("${file.images.root-path}")
    private String fileRootPath;

    @Override
    public byte[] getImage(String folderName, String fileName) throws IOException {
        Path path = Paths.get(fileRootPath, folderName);
        String imageAbsolutePath = new StringBuilder(path.toString())
                .append(FileSystems.getDefault().getSeparator())
                .append(fileName).toString();
        byte[] image = FileUtils.readFileToByteArray(new File(imageAbsolutePath));
        return image;
    }

    @Override
    public byte[] putImage(String folderName, String fileName) {
        return new byte[0];
    }
}
