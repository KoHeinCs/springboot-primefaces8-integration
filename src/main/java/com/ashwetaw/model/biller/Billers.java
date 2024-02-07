package com.ashwetaw.model.biller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author heinhtet_aung
 * @created 7/24/2023
 **/
@Document(collection = "billers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billers {
    @Id
    private String id;
    private String billerTemplateID;
    private String name;
    private String backendAggregator;
    private String merchantCode;
    private String aggregator;
    private String apiIndicator = "2";
    private String sendCharge = "0";
    private String isCustomerNumberRequired = "true";
    private String isBillReferenceRequired = "false";
    private String keyword = "";
    private String featureName = "";
    private String offset = "0";
    private String percentage = "0";
    private String type = "OFFLINE";
    private Boolean flexiOnly;
    private Boolean allowLegacyToFlexi;
    private String billerCategory;
    private String paymentType;
    private int step = 1;
    private String category;
    private String billLogo;
    private String categoryLogo;
    private List<FieldDetail> fieldsDetails;
}
