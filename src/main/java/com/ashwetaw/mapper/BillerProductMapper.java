package com.ashwetaw.mapper;

import com.ashwetaw.dto.BillerProductDTO;
import com.ashwetaw.model.productbiller.BillerProduct;
import org.mapstruct.Mapper;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Mapper(componentModel = "spring")
public interface BillerProductMapper {
    BillerProduct dtoToEntity(BillerProductDTO billerProductDTO);
}
