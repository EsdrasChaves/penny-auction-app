/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author esdra
 */
public class FacesUtil {
    
    public static boolean isPostBak() {
        return FacesContext.getCurrentInstance().isPostback();
    }
    
    public static boolean isNotPostBack() {
        return !isPostBak();
    }
    
    public static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance()
                .addMessage(
                        null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message,
                                message));
    }
    
    public static void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
    
}
