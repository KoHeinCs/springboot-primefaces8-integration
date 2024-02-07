package com.ashwetaw.model.billerconfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "biller-configuration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillerConfiguration {

    @Id
    private String id;
    private ApiConfig apiConfig;
    private BillerConfig billerConfig;
    private String billerName;
    private String chargeCode = "BP002";
    private String confirm = "false";
    private Boolean emailRequired;
    private String fee = "0";
    private String feeType = "fixed";
    private PartnerMailConfig partnerMailConfig;
    private String protocol = "GRPC";
}
