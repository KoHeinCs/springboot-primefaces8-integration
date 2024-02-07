
package com.ashwetaw.model.billerconfiguration;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PartnerMailConfig {

    private String ccAddress;
    private String clientName;
    private String clientSecret;
    private String emailTemplateId;
    private String subject;
    private String toAddress;

}
