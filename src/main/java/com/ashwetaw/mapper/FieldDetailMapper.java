package com.ashwetaw.mapper;

import com.ashwetaw.dto.FieldDetailDTO;
import com.ashwetaw.model.biller.FieldDetail;
import org.mapstruct.Mapper;

/**
 * @author heinhtet_aung
 * @created 10/8/2023
 **/
@Mapper(componentModel = "spring")
public interface FieldDetailMapper {
    FieldDetail dtoToEntity(FieldDetailDTO fieldDetailDTO);
}
