package com.ashwetaw.service;

import com.ashwetaw.dto.ProductDTO;
import com.ashwetaw.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author heinhtet_aung
 * @created 10/12/2023
 **/
public interface ProductService {
    Map<String, Object>  isValidAccount(Map<String, ProductDTO> productDTOMap);
    ProductDTO getFirstLegacyAccountWithoutReversal(Map<String, ProductDTO> productDTOMap);
    Optional<ProductDTO> findDuplicateAccountNature(Map<String, ProductDTO> productDTOMap,ProductDTO product);
    List<Product> saveAll(Map<String, ProductDTO> productDTOMap);
}
