package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.common.service.BaseService;
import com.kisaan.jai.dto.ExpertOrderDTO;
import java.util.List;

public interface ExpertOrderService extends BaseService<ExpertOrderDTO, Long>,
        BaseGetService<ExpertOrderDTO, Long> {

    List<ExpertOrderDTO> getAllByFarmerIdAndExpertId(Long farmerId, Long expertId);

    ExpertOrderDTO getExpertOrderIdAndFarmerIdAndExpertId(Long id, Long farmerId, Long expertId);
}
