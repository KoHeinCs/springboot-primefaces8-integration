package com.ashwetaw.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Data
public class BillerProductDTO {
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
}
