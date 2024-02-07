package com.ashwetaw.repositories.primary;

import com.ashwetaw.model.productbiller.BillerProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
@Repository
public interface BillerProductRepository  extends MongoRepository<BillerProduct, String> {
}
