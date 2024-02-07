package com.ashwetaw.model.productbiller;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Data
@Document(collection = "biller-product")
public class BillerProduct {
    @Id
    private String objectId;
    String billerTemplateID;
    String productId;
    String feesPaidBy;
    Boolean isFeesShared;
    Double feesByCustomer;
    Double feesByBiller;
    Double feesByYomaBank;
    String billerFeesAccount;
    Double billerCommission;
    Double YBCommission;
    List<PartnerConfig> partnerConfigs;
    LocalDateTime createdDate;
}
