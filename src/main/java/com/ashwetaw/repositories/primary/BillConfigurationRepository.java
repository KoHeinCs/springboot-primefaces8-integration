package com.ashwetaw.repositories.primary;

import com.ashwetaw.model.billerconfiguration.BillerConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author heinhtet_aung
 * @created 10/5/2023
 **/
@Repository
public interface BillConfigurationRepository extends MongoRepository<BillerConfiguration, String> {

}
