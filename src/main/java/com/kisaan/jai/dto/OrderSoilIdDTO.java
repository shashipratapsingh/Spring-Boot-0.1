package com.kisaan.jai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSoilIdDTO extends FarmerIdDTO{

    @NotNull(message = "order id can't be empty")
    private Long orderId;


}