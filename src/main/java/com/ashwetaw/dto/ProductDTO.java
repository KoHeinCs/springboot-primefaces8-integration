package com.ashwetaw.dto;

import com.ashwetaw.model.biller.Billers;
import lombok.Data;

/**
 * @author heinhtet_aung
 * @created 10/8/2023
 **/
@Data
public class ProductDTO {

    private String tempId;
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

    private String productAcctType;
    private Boolean productReversal;

    public static  ProductDTO getProductForLegacy(Billers billers){
        ProductDTO productDTO = new ProductDTO();
        productDTO.tempId = System.nanoTime() + "";
        productDTO.serviceCode = billers.getBillerTemplateID();
        productDTO.productName = productDTO.serviceCode+"Biller";
        productDTO.fees = 0L;
        productDTO.feeType = "fixed";
        productDTO.transactionCode = "CWS";
        productDTO.transactionCodeFee = productDTO.transactionCode;
        productDTO.narrative = billers.getName()+" Transfer";
        productDTO.narrativeFee = productDTO.narrative+" Charge";
        productDTO.productAcctType = "legacy";
        productDTO.productReversal = false;
        return productDTO;
    }

    public static ProductDTO getReversalProductForLegacy(Billers billers){
        ProductDTO productDTO = new ProductDTO();
        productDTO.tempId = System.nanoTime() + "";
        productDTO.serviceCode = billers.getBillerTemplateID();
        productDTO.productName = productDTO.serviceCode+"BillerReversal";
        productDTO.fees = 0L;
        productDTO.feeType = "fixed";
        productDTO.transactionCode = "CWS";
        productDTO.transactionCodeFee = productDTO.transactionCode;
        productDTO.narrative = billers.getName()+" Transfer Reversal";
        productDTO.narrativeFee = billers.getName()+" Transfer Charge";
        productDTO.productAcctType = "legacy";
        productDTO.productReversal = true;
        return productDTO;
    }

    public static ProductDTO getProductForFlexi(Billers billers){
        ProductDTO productDTO = new ProductDTO();
        productDTO.tempId = System.nanoTime() + "";
        productDTO.serviceCode = billers.getBillerTemplateID();
        productDTO.productName = productDTO.serviceCode+"BillerFlexi";
        productDTO.fees = 0L;
        productDTO.feeType = "fixed";
        productDTO.transactionCode = "CWS";
        productDTO.transactionCodeFee = productDTO.transactionCode;
        productDTO.narrative = billers.getName()+" Transfer";
        productDTO.narrativeFee = productDTO.narrative+" Charge";
        productDTO.productAcctType = "flexi";
        productDTO.productReversal = false;
        return productDTO;
    }

    public static ProductDTO getReversalProductForFlexi(Billers billers){
        ProductDTO productDTO = new ProductDTO();
        productDTO.tempId = System.nanoTime() + "";
        productDTO.serviceCode = billers.getBillerTemplateID();
        productDTO.productName = productDTO.serviceCode+"BillerReversalFlexi";
        productDTO.fees = 0L;
        productDTO.feeType = "fixed";
        productDTO.transactionCode = "CWS";
        productDTO.transactionCodeFee = productDTO.transactionCode;
        productDTO.narrative = billers.getName()+" Transfer Reversal";
        productDTO.narrativeFee = billers.getName()+" Transfer Charge";
        productDTO.productAcctType = "flexi";
        productDTO.productReversal = true;
        return productDTO;
    }

}
