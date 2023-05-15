package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.dto.OrderSoilDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface OrderSoilService extends BaseService<OrderSoilDTO, Long> {

    List<OrderSoilDTO> getAllByFarmerId(Long id);
    
    OrderSoilDTO getByFarmerIdAndId(Long farmerId, Long id);

    List<OrderSoilDTO> getAllByFarmerIdAndDateRange(Long id, ZonedDateTime startDate, ZonedDateTime endDate);
}
