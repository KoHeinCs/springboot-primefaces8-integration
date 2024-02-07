package com.ashwetaw.config.listener;

import com.ashwetaw.common.Constants;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author heinhtet_aung
 * @created 10/11/2023
 **/
public class ViewListener  implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletRequest httpRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        if (httpRequest.getRequestedSessionId() != null && !httpRequest.isRequestedSessionIdValid()) {
            String facesRequestHeader = httpRequest.getHeader("Faces-Request");
            boolean isAjaxRequest = facesRequestHeader != null && facesRequestHeader.equals("partial/ajax");

            if (isAjaxRequest) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constants.VIEW_EXPIRED, true);
                ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
                handler.performNavigation("welcome");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
