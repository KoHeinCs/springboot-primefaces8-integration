package com.ashwetaw.model.productbiller;

import lombok.Data;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
@Data
public class PartnerConfig {
    String partnerCode;
    double billerCommission;
    double partnerCommission;
    double YBCommission;
}
