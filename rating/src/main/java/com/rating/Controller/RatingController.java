package com.rating.Controller;

import com.rating.Helper.FileUploadHandler;
import com.rating.service.RatingService;
import com.rating.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private FileUploadHandler fileUploadHandler;

    @PostMapping("/addRating")
    public Rating saveDate(@RequestBody Rating rating) {

        return ratingService.saveDate(rating);
    }

    @GetMapping("/getData/{id}")
    public Rating getById(@PathVariable int id) {
        // return ratingService.getById(id);
        return ratingService.getById(id);
    }

    @GetMapping("/getListOfRating")
    public List<Rating> getListOfUser() {

        return ratingService.getListOfUser();
    }

    @DeleteMapping("/deleteByID/{id}")
    public void deleteByID(@PathVariable int id) {

        ratingService.deleteById(id);
    }


    //special type of methode
    @GetMapping("/getRatingByUserId/{UserId}")
    public List<Rating> findByUserId(@PathVariable(value = "UserId") String UserId) {
        return ratingService.findByUserId(UserId);
    }

    @GetMapping("/getRatingByHotelId/{HotelId}")
    public List<Rating> findByHotelId(@PathVariable(value = "HotelId") String HotelId) {
        return ratingService.findByHotelId(HotelId);
    }

    //file upload

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        /*file.getContentType();
        file.getName();
        file.getSize();
        file.getContentType();
        file.getOriginalFilename();*/


        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request to file upload");
            }
            if (!file.getContentType().equals("image/png")) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpeg allowed");


            }
            //file upload code
            boolean f = fileUploadHandler.uploadFile(file);
            if (f) {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/img/").path(file.getOriginalFilename()).toUriString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
