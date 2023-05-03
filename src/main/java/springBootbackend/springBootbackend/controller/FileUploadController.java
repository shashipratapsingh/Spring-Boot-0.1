package springBootbackend.springBootbackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springBootbackend.springBootbackend.Utils.FileUploadUtil;
import springBootbackend.springBootbackend.model.FileUploadResponse;
import springBootbackend.springBootbackend.repository.UploadRepository;


@RequestMapping("/v1")
@RestController
public class FileUploadController {
	
	@Autowired
	private UploadRepository uploadRepository;
     
    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
    		FileUploadResponse fileUploadResponse,
            @RequestParam("file") MultipartFile multipartFile)
                    throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
         
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
         
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);
        this.uploadRepository.save(response);
         
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}