package com.ashwetaw.service;

import com.ashwetaw.dto.FieldDetailDTO;
import com.ashwetaw.model.biller.Billers;
import com.ashwetaw.model.biller.FieldDetail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author heinhtet_aung
 * @created 10/12/2023
 **/

public interface BillerService {
    List<Billers>  findByBillerTemplateID(String billerTemplateId);
    List<String>  findAllDistinctBillerCategories();
    List<FieldDetail> getFieldDetailList(Map<String, FieldDetailDTO> fieldDetailDTOMap);
    Optional<FieldDetailDTO>  findDuplicateField(Map<String, FieldDetailDTO> fieldDetailDTOMap, FieldDetailDTO field1);
    void save(Billers billers);
}
