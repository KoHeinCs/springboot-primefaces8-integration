
package com.ashwetaw.model.billerconfiguration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillerConfig {

    private List<Object> additionalFields;
    private ProductInfoConfig productInfoConfig;

}
