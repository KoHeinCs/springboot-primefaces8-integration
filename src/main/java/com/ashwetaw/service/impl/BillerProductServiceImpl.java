package com.ashwetaw.service.impl;

import com.ashwetaw.dto.BillerProductDTO;
import com.ashwetaw.mapper.BillerProductMapper;
import com.ashwetaw.model.product.Product;
import com.ashwetaw.model.productbiller.BillerProduct;
import com.ashwetaw.repositories.primary.BillerProductRepository;
import com.ashwetaw.service.BillerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
@Service
@RequiredArgsConstructor
public class BillerProductServiceImpl implements BillerProductService {
    private final BillerProductRepository billerProductRepository;
    private final BillerProductMapper billerProductMapper;

    @Override
    public void saveAllBillerProduct(List<Product> productList, BillerProductDTO billerProductDTO) {

        List<BillerProduct> billerProductList = new ArrayList<>();

        for (Product product:productList) {
            BillerProduct billerProduct  = billerProductMapper.dtoToEntity(billerProductDTO);
            billerProduct.setProductId(product.getId());
            billerProductList.add(billerProduct);
        }
        billerProductRepository.saveAll(billerProductList);
    }
}
