package com.kisaan.jai.payload.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> prepareResponse(ApiResponse apiResponse) {

        if (apiResponse.getHttpStatus().value() >= 200 && apiResponse.getHttpStatus().value() <= 299) {
            apiResponse.setStatus(Boolean.TRUE);
        } else {
            apiResponse.setStatus(Boolean.FALSE);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
