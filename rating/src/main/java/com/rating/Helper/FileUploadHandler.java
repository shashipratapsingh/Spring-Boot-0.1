package com.rating.Helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHandler {
   // public final String UPLOAD_DIR = "D:\\extra\\rating\\src\\main\\resources\\static\\img";
   public final String UPLOAD_DIR = new ClassPathResource("static/img").getFile().getAbsolutePath();

    public FileUploadHandler() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean f = false;
        try {
            //
            InputStream inputStream = multipartFile.getInputStream();
            byte data[] = new byte[inputStream.available()];
            inputStream.read(data);

            //write
           /* FileOutputStream fileOutputStream=new FileOutputStream((UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()));
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();*/

            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR + File.separator
                    + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return f;
    }
}
