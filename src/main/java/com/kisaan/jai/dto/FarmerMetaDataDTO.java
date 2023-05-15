package com.kisaan.jai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FarmerMetaDataDTO {

    private Long farmerId;

    private String name;

    private String fatherName;

    private String address;

    private String mobileNumber;
}
