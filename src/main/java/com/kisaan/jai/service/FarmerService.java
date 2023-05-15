package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.dto.FarmerDTO;

import java.util.List;

public interface FarmerService extends BaseService<FarmerDTO, Long>,
        BaseGetService<FarmerDTO, Long> {

}
