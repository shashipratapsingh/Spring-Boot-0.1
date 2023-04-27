package com.HotelService.Handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse1 {

        private final Object data;
        private final Boolean success;
        private final String timestamp;
        private final String cause;
        private final String path;

        public ApiResponse1(Boolean success, Object data) {
            this.timestamp = Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toString();
            this.data = data;
            this.success = success;
            this.cause = null;
            this.path = null;
        }


}

