package com.ashwetaw.dto;

import com.ashwetaw.model.biller.InputType;
import lombok.Data;

/**
 * @author heinhtet_aung
 * @created 10/6/2023
 **/
@Data
public class FieldDetailDTO {
    private String tempId;
    private String key;
    private InputType inputType;
    private String hint;
    private String label;
    private String validationRegex;
    private String validationErrorMessage;
    private String mappingColumn;
    private Boolean showInSuccess;
    private Boolean isMandatory;
}
