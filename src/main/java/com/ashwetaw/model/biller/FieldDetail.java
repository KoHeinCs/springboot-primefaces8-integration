package com.ashwetaw.model.biller;

import lombok.Data;

/**
 * @author heinhtet_aung
 * @created 7/24/2023
 **/
@Data
public class FieldDetail {
    private String key;
    private InputType inputType;
    private String hint;
    private String label;
    private String validationRegex;
    private String validationErrorMessage;
    private String mappingColumn;
    private Boolean showInSuccess;
}
