package com.ashwetaw.controller;

import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author heinhtet_aung
 * @created 10/5/2023
 **/
@ManagedBean
@RequestScoped
@Component
public class MenuActionBean {

    public String openFile() {
        return "manageOfflineBiller"; // Navigation outcome
    }
}
