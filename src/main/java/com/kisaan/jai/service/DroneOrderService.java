package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.dto.DroneOrderDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface DroneOrderService extends BaseService<DroneOrderDTO, Long>,
        BaseGetService<DroneOrderDTO, Long> {

    List<DroneOrderDTO> getAllByFarmerId(Long id);
    
    DroneOrderDTO getDroneOrderFarmerIdAndId(Long farmerId, Long id);

    List<DroneOrderDTO> getAllByFarmerIdAndDateRange(Long id, ZonedDateTime startDate, ZonedDateTime endDate);
}