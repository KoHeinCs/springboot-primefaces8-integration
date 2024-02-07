package com.ashwetaw.service.impl;

import com.ashwetaw.dto.FieldDetailDTO;
import com.ashwetaw.mapper.FieldDetailMapper;
import com.ashwetaw.model.biller.Billers;
import com.ashwetaw.model.biller.FieldDetail;
import com.ashwetaw.repositories.primary.BillerRepository;
import com.ashwetaw.service.BillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author heinhtet_aung
 * @created 10/12/2023
 **/
@Service
@RequiredArgsConstructor
public class BillerServiceImpl implements BillerService {
    private final BillerRepository billerRepository;
    private final FieldDetailMapper fieldDetailMapper;

    @Override
    public List<Billers> findByBillerTemplateID(String billerTemplateId) {
        return billerRepository.findByBillerTemplateID(billerTemplateId);
    }

    @Override
    public List<String>  findAllDistinctBillerCategories() {

        List<String> billerCategories = new ArrayList<>();
        Set<String> distinctBillerCategories = billerRepository.findAllDistinctBillerCategories();
        distinctBillerCategories.stream()
                .map(category -> category.split(":"))
                .filter(parts -> parts.length == 2)
                .map(parts -> parts[1].replaceAll("[\"}]", "").trim())
                .filter(val -> !val.equalsIgnoreCase(""))
                .forEach(billerCategories::add);

        return billerCategories;
    }

    @Override
    public List<FieldDetail> getFieldDetailList(Map<String, FieldDetailDTO> fieldDetailDTOMap) {
        List<FieldDetail> result = new ArrayList<>();
        if (fieldDetailDTOMap.values() != null) {
            for (FieldDetailDTO fieldDetailDTO : fieldDetailDTOMap.values()) {
                result.add(fieldDetailMapper.dtoToEntity(fieldDetailDTO));
            }
        }
        return result;
    }

    @Override
    public Optional<FieldDetailDTO> findDuplicateField(Map<String, FieldDetailDTO> fieldDetailDTOMap, FieldDetailDTO field1) {
        return fieldDetailDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(field2 -> field1.getKey().trim().equalsIgnoreCase(field2.getKey().trim()))
                .findFirst();
    }

    @Override
    public void save(Billers billers) {
         billerRepository.save(billers);
    }
}
