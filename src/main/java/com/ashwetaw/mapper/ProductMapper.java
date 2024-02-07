package com.ashwetaw.mapper;

import com.ashwetaw.dto.ProductDTO;
import com.ashwetaw.model.product.Product;
import org.mapstruct.Mapper;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToEntity(ProductDTO productDTO);
}
