/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author esdra
 */

public class SessionUtil implements Serializable {
    public static HttpSession getSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        
        return session;
    }
    
    public static void setParam(String key, Object value) {
        getSession().setAttribute(key, value);
    }
    
    public static Object getParam(String key) {
        return getSession().getAttribute(key);
    }
    
    public static boolean isLogged(String key) {
        if(getSession().getAttribute(key) != null)
            return true;
        else
            return false;
    }
    
    public static void remove(String key) {
        getSession().removeAttribute(key);
    }
    
    public static void invalidate() {
        getSession().invalidate();
    }
}
