
package com.ashwetaw.model.billerconfiguration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiConfig {
    private List<Object> headers;
    private Url url;
}
