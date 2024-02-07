package com.ashwetaw.controller;

import com.ashwetaw.common.BaseBean;
import com.ashwetaw.common.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;

/**
 * @author heinhtet_aung
 * @created 10/13/2023
 **/
@ViewScoped
@ManagedBean
@Getter
@Setter
@Component
public class WelcomeActionBean extends BaseBean implements Serializable {



    public void checkMessage(ComponentSystemEvent event) {
        ExternalContext extContext = getFacesContext().getExternalContext();
        String messageId = (String) extContext.getSessionMap().get(Constants.MESSAGE_ID);
        String billerId = (String) extContext.getSessionMap().get(Constants.BILLER_ID);
        if (messageId != null) {
            if (billerId != null) {
                addInfoMessage(null, messageId +billerId);
                extContext.getSessionMap().remove(Constants.MESSAGE_ID);
                extContext.getSessionMap().remove(Constants.BILLER_ID);
            }
        }
    }

}
