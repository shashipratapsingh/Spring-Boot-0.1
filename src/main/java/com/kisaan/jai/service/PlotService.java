package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.dto.PlotDTO;

import java.util.List;

public interface PlotService extends BaseService<PlotDTO, Long>,
        BaseGetService<PlotDTO, Long> {

    List<PlotDTO> getPlotsByFarmerId(Long farmerId);
}
