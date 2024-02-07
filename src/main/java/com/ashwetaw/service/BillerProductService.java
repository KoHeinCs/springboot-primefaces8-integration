package com.ashwetaw.service;

import com.ashwetaw.dto.BillerProductDTO;
import com.ashwetaw.model.product.Product;

import java.util.List;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
public interface BillerProductService {
    void saveAllBillerProduct(List<Product> productList, BillerProductDTO billerProductDTO);
}
