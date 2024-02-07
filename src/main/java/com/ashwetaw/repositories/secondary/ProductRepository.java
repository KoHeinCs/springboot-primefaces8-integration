package com.ashwetaw.repositories.secondary;

import com.ashwetaw.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
