package com.ashwetaw.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.MissingResourceException;

/**
 * @author heinhtet_aung
 * @created 10/6/2023
 **/
public class BaseBean {

    protected static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected void addInfoMessage(String id, String errorCode, Object... params) {
        String message = getMessage(errorCode, params);
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }


    protected void addErrorMessage(String id, String errorCode, Object... params) {
        String message = getMessage(errorCode, params);
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, message,message));
    }

    public static String getMessage(String errorCode, Object... params) {
        String text = null;
        try {
            text = errorCode;
        } catch (MissingResourceException e) {
            text = "!! key " + errorCode + " not found !!";
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(text, params);
        }
        return text;
    }


}
