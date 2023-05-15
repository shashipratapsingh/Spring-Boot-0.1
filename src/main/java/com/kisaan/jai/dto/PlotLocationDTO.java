package com.kisaan.jai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlotLocationDTO {

    private Long districtId;

    private Long tahsilId;

    private Long villageId;

    private String longitude;

    private String latitude;
}
