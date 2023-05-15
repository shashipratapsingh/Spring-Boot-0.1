package com.kisaan.jai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MobileNumberDTO {

    @NotBlank(message = "Mobile number can't be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Please enter valid 10 digits mobile number")
    private String mobile;
}