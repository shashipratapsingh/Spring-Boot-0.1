package com.kisaan.jai.service;

import com.kisaan.jai.common.service.BaseGetService;
import com.kisaan.jai.dto.VillageDTO;
import com.kisaan.jai.dto.VillageMetaDataDTO;

import java.util.List;

public interface VillageService extends BaseGetService<VillageDTO, Long> {

    List<VillageDTO> getVillagesByMetaData(VillageMetaDataDTO villageMetaDataDTO);
}
