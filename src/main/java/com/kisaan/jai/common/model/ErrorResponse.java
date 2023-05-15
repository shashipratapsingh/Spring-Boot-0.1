package com.kisaan.jai.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private int errCode;

    private String errMessage;
}
