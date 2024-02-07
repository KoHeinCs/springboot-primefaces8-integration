package com.ashwetaw.controller;

import com.ashwetaw.common.BaseBean;
import com.ashwetaw.common.Constants;
import com.ashwetaw.common.MessageId;
import com.ashwetaw.dto.BillerProductDTO;
import com.ashwetaw.dto.FieldDetailDTO;
import com.ashwetaw.dto.ProductDTO;
import com.ashwetaw.mapper.FieldDetailMapper;
import com.ashwetaw.model.biller.Billers;
import com.ashwetaw.model.biller.FieldDetail;
import com.ashwetaw.model.biller.InputType;
import com.ashwetaw.model.billerconfiguration.ApiConfig;
import com.ashwetaw.model.billerconfiguration.BillerConfiguration;
import com.ashwetaw.model.billerconfiguration.Url;
import com.ashwetaw.model.product.Product;
import com.ashwetaw.repositories.primary.BillerRepository;
import com.ashwetaw.service.BillConfigService;
import com.ashwetaw.service.BillerProductService;
import com.ashwetaw.service.BillerService;
import com.ashwetaw.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FlowEvent;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.*;

/**
 * @author heinhtet_aung
 * @created 7/24/2023
 **/

@ManagedBean
@ViewScoped
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class ManageOfflineBiller extends BaseBean implements Serializable {

    private final BillerService billerService;
    private final ProductService productService;
    private final BillConfigService billConfigService;
    private final BillerProductService billerProductService;


    private Billers billers;
    private BillerConfiguration billerConfig;
    private Url url;

    private FieldDetailDTO fieldDetailDTO;
    private Map<String, FieldDetailDTO> fieldDetailDTOMap;

    private ProductDTO productDTO;
    private Map<String, ProductDTO> productDTOMap;

    private BillerProductDTO billerProductDTO;

    private List<String> billerCategories;

    private boolean createNewFieldDetailInfo;

    private boolean createProductInfo;

    public void initialize() {

        this.billers = new Billers();
        this.billerProductDTO = new BillerProductDTO();
        this.billerConfig = new BillerConfiguration();
        this.url = new Url();

        createNewFieldDetailInfo();
        this.fieldDetailDTOMap = new HashMap<>();

        createNewProductInfo();
        this.productDTOMap = new HashMap<>();

        this.billerCategories = billerService.findAllDistinctBillerCategories();
    }

    public String addNewBiller() {

        billerService.save(this.billers);
        billConfigService.save(this.billerConfig);
        List<Product> productList = productService.saveAll(this.productDTOMap);
        billerProductService.saveAllBillerProduct(productList, this.billerProductDTO);

        ExternalContext extContext = getFacesContext().getExternalContext();
        extContext.getSessionMap().put(Constants.MESSAGE_ID, MessageId.BILLER_PROCESS_SUCCESS);
        extContext.getSessionMap().put(Constants.BILLER_ID, this.billers.getBillerTemplateID());

        return "welcome";
    }


    public String onFlowProcess(FlowEvent event) {

        boolean valid = true;
        if ("fields".equals(event.getOldStep()) && "billerConfiguration".equals(event.getNewStep())) {

            String billerTemplateId = this.billers.getBillerTemplateID();
            List<Billers> billersList = billerService.findByBillerTemplateID(billerTemplateId);

            if (!billersList.isEmpty()) {
                addErrorMessage(null, "Biller template id already exits !");
                PrimeFaces.current().ajax().update("offlineBillerEntiryForm");
                valid = false;
            } else {
                int fieldSize = getFieldDetailDTOList().size();
                if (fieldSize < 1) {
                    addErrorMessage("offlineBillerEntiryForm:billerFieldDetailTable", "At least one field detail must have.");
                    valid = false;
                } else {
                    this.billers.setFieldsDetails(billerService.getFieldDetailList(this.fieldDetailDTOMap));
                    this.billerConfig.setBillerName(billerTemplateId);
                }

            }

        }

        if ("billerConfiguration".equals(event.getOldStep()) && "product".equals(event.getNewStep())) {

            ApiConfig apiConfig = new ApiConfig();
            apiConfig.setUrl(url);
            this.billerConfig.setApiConfig(apiConfig);

        }

        if ("product".equals(event.getOldStep()) && "billerProduct".equals(event.getNewStep())) {
            Map<String, Object> result = productService.isValidAccount(this.productDTOMap);
            // Retrieve the boolean value
            valid = (boolean) result.get("isValie");
            if (!valid) {
                String errMsg = (String) result.get("errMsg");
                addErrorMessage("offlineBillerEntiryForm:productDTOTable", errMsg);
            } else {
                this.billerProductDTO.setBillerTemplateID(billers.getBillerTemplateID());
            }
        }

        return valid ? event.getNewStep() : event.getOldStep();
    }

    /**
     * Start Field CRUD Session
     **/

    private void createNewFieldDetailInfo() {
        this.createNewFieldDetailInfo = true;
        InputType inputType = new InputType();
        this.fieldDetailDTO = new FieldDetailDTO();
        this.fieldDetailDTO.setInputType(inputType);
    }

    public void saveFieldsInfo() {
        this.fieldDetailDTO.setTempId(System.nanoTime() + "");
        Optional<FieldDetailDTO> result = billerService.findDuplicateField(this.fieldDetailDTOMap, this.fieldDetailDTO);
        if (result.isPresent()) {
            addErrorMessage(null, "Duplicate  Key Detected !");
            PrimeFaces.current().ajax().update("offlineBillerEntiryForm");
        } else {
            this.fieldDetailDTOMap.put(this.fieldDetailDTO.getTempId(), this.fieldDetailDTO);
            createNewFieldDetailInfo();
        }

    }

    public void viewFieldDetailInfo(FieldDetailDTO fieldDetailDTO) {
        this.fieldDetailDTO = fieldDetailDTO;
    }

    public void removeFieldDetailInfo(FieldDetailDTO fieldDetailDTO) {
        this.fieldDetailDTOMap.remove(fieldDetailDTO.getTempId());
        createNewFieldDetailInfo();
    }

    /** End Field CRUD Session **/

    /**
     * Start Account CRUD Session
     **/

    public void prepareAddNewProductInfo() {
        createNewProductInfo();
    }

    private void createNewProductInfo() {
        this.createProductInfo = true;
        this.productDTO = new ProductDTO();
    }

    public void viewProductInfo(ProductDTO productDTO) {
        this.createProductInfo = false;
        this.productDTO = productDTO;
    }

    public void removeProductInfo(ProductDTO productDTO) {
        this.productDTOMap.remove(productDTO.getTempId());
        createNewProductInfo();
    }

    public void saveProductInfo() {
        Optional<ProductDTO> productDTOOpt = productService.findDuplicateAccountNature(this.productDTOMap, this.productDTO);
        if (productDTOOpt.isPresent()) {
            addErrorMessage(null, "Duplicate Account Nature Detected !");
            PrimeFaces.current().ajax().update("offlineBillerEntiryForm");
        } else {
            this.productDTOMap.put(this.productDTO.getTempId(), this.productDTO);
            createNewProductInfo();
            PrimeFaces.current().executeScript("PF('productInfoEntryDialog').hide()");
        }

    }

    /** End Account CRUD Session **/

    /**
     * Start Event Listener Session
     **/

    public void changeEmailRequiredEvent(AjaxBehaviorEvent event) {

    }

    public void changeFeePaidByEvent(AjaxBehaviorEvent event) {
        if (this.billerProductDTO.getFeesPaidBy().equals("customer")) {
            this.billerProductDTO.setFeesByBiller(0d);
            this.billerProductDTO.setFeesByYomaBank(0d);
        }

    }

    public void changeFieldDetailOptionalEvent(AjaxBehaviorEvent event) {
        if (!fieldDetailDTO.getIsMandatory()) {
            fieldDetailDTO.setValidationRegex("");
            fieldDetailDTO.setValidationErrorMessage("");
        }
    }

    public void changeProductAccountTypeEvent(AjaxBehaviorEvent event) {
        this.productDTO.setProductReversal(null);
    }

    public void changeTransactionTypeEvent(AjaxBehaviorEvent event) {
        String acctType = this.productDTO.getProductAcctType();
        Boolean isReversal = this.productDTO.getProductReversal();
        this.productDTO = null;
        if (acctType.equalsIgnoreCase("legacy") && !isReversal) {
            this.productDTO = ProductDTO.getProductForLegacy(billers);
        } else if (acctType.equalsIgnoreCase("legacy") && isReversal) {
            this.productDTO = ProductDTO.getReversalProductForLegacy(billers);
        } else if (acctType.equalsIgnoreCase("flexi") && !isReversal) {
            this.productDTO = ProductDTO.getProductForFlexi(billers);
        } else {
            this.productDTO = ProductDTO.getReversalProductForFlexi(billers);
        }

        PrimeFaces.current().ajax().update("productInfoEntryForm");

    }

    public void updateTargetValueBasedOnBillerTemplate(AjaxBehaviorEvent event) {
        String billerTemplateId = this.billers.getBillerTemplateID();
        List<Billers> billersList = billerService.findByBillerTemplateID(billerTemplateId);
        if (!billersList.isEmpty()) {
            this.billers.setBackendAggregator(null);
            this.billers.setMerchantCode(null);
            this.billers.setAggregator(null);
            addErrorMessage(null, "Biller template id already exits !");
        } else {
            this.billers.setBackendAggregator(billerTemplateId);
            this.billers.setMerchantCode(billerTemplateId);
            this.billers.setAggregator(billerTemplateId);
        }

    }

    public void changeFieldDetailFieldTypeEvent(AjaxBehaviorEvent event) {
        if (!fieldDetailDTO.getInputType().getType().equals("number")) {
            this.fieldDetailDTO.getInputType().setAndroidDigits("");
        }
    }

    public void handleFieldDialogClose(CloseEvent event) {
        createNewFieldDetailInfo();
    }

    /**
     * End Event Listener Session
     **/

    public List<FieldDetailDTO> getFieldDetailDTOList() {
        return new ArrayList<>(this.fieldDetailDTOMap.values());
    }

    public List<ProductDTO> getProductDTOList() {
        return new ArrayList<>(this.productDTOMap.values());
    }

    public ProductDTO getFirstLegacyWithoutReversal() {
        return productService.getFirstLegacyAccountWithoutReversal(this.productDTOMap);
    }


}
