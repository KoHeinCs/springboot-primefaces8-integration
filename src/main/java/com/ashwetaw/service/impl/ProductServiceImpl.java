package com.ashwetaw.service.impl;

import com.ashwetaw.dto.FieldDetailDTO;
import com.ashwetaw.dto.ProductDTO;
import com.ashwetaw.mapper.ProductMapper;
import com.ashwetaw.model.biller.FieldDetail;
import com.ashwetaw.model.product.Product;
import com.ashwetaw.repositories.secondary.ProductRepository;
import com.ashwetaw.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author heinhtet_aung
 * @created 10/12/2023
 **/
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Map<String, Object> isValidAccount(Map<String, ProductDTO> productDTOMap) {

        Map<String, Object> result = new HashMap<>();
        result.put("isValie", true);
        result.put("errMsg", "OK");


        // Find "legacy, not reversal" account
        ProductDTO legacyNotReversalAccount = productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(account -> account.getProductAcctType().equals("legacy") && !account.getProductReversal())
                .findFirst()
                .orElse(null);

        // Find "legacy, reversal" account
        ProductDTO legacyReversalAccount = productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(account -> account.getProductAcctType().equals("legacy") && account.getProductReversal())
                .findFirst()
                .orElse(null);

        // Find "flexi, not reversal" account
        ProductDTO flexiNotReversalAccount = productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(account -> account.getProductAcctType().equals("flexi") && !account.getProductReversal())
                .findFirst()
                .orElse(null);

        // Find "flexi, reversal" account
        ProductDTO flexiReversalAccount = productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(account -> account.getProductAcctType().equals("flexi") && account.getProductReversal())
                .findFirst()
                .orElse(null);


        if (Objects.isNull(legacyNotReversalAccount)) {
            result.put("isValie", false);
            result.put("errMsg", "Please create  (legacy without reversal) account !");
        } else if (Objects.isNull(legacyReversalAccount)) {
            result.put("isValie", false);
            result.put("errMsg", "Please create  (legacy with reversal) account !");
        } else if (Objects.nonNull(flexiReversalAccount) && Objects.isNull(flexiNotReversalAccount)) {
            result.put("isValie", false);
            result.put("errMsg", "Please create (flexi without reversal)  account !");

        } else if (Objects.nonNull(flexiNotReversalAccount) && Objects.isNull(flexiReversalAccount)) {
            result.put("isValie", false);
            result.put("errMsg", "Please create (flexi with reversal) account !");
        }

        return result;
    }

    @Override
    public ProductDTO getFirstLegacyAccountWithoutReversal(Map<String, ProductDTO> productDTOMap) {

        return productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(product1 -> product1.getProductAcctType().equals("legacy") && !product1.getProductReversal())
                .findFirst().get();
    }

    @Override
    public Optional<ProductDTO> findDuplicateAccountNature(Map<String, ProductDTO> productDTOMap,ProductDTO product1) {
        return productDTOMap.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(product2 ->
                        product2.getProductAcctType().equals(product1.getProductAcctType()) &&
                                product2.getProductReversal().equals(product1.getProductReversal())
                )
                .findFirst();
    }

    @Override
    public List<Product> saveAll(Map<String, ProductDTO> productDTOMap) {

        List<Product> productList = new ArrayList<>();

        if (productDTOMap.values() != null) {
            for (ProductDTO productDTO : productDTOMap.values()) {
                productList.add(productMapper.dtoToEntity(productDTO));
            }
        }
       return productRepository.saveAll(productList);
    }
}
