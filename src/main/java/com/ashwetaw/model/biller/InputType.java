package com.ashwetaw.model.biller;

import lombok.Data;

import java.util.List;

/**
 * @author heinhtet_aung
 * @created 7/24/2023
 **/

@Data
public class InputType {
    private String type;
    private List<DropDownValues> array;
    private String androidDigits;
}
