package com.ashwetaw.model.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String id;
    private String chargesAccount;
    private String collectionAccount;
    private String feeType;
    private Long fees;
    private String incomeAccount;
    private String narrative;
    private String narrativeFee;
    private String productName;
    private String serviceCode;
    private String serviceName;
    private String sundryAccount;
    private String transactionCode;
    private String transactionCodeFee;

}
