/**
 * 
 */
package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginDetailDTO extends MobileNumberDTO{

	@Pattern(regexp = "^\\d{4}$", message = "Please enter valid 4 digits OTP")
	private String otp;

	private Long farmerId;
}
