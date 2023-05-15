package com.kisaan.jai.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"httpStatus"})
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonPropertyOrder({ "status", "message", "count"})
public class ApiResponse {

	private String message;

	private HttpStatus httpStatus;

	private Boolean status ;

	private Object responseBody;

	private String farmerId;

	private Integer count;

	private String isRegistered;

	private String orderId;

	private String productId;
}
