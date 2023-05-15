package com.kisaan.jai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerOrderDetailsDTO {

    private List<DroneOrderDTO> dronOrders;
    private List<OrderSoilDTO> soilOrders;
    private List<ProductOrderDTO> productOrders;

}