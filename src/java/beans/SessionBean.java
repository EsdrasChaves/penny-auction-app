/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.SessionUtil;

/**
 *
 * @author esdra
 */


@SessionScoped
@ManagedBean
public class SessionBean {
    
    public boolean isLogged() {
        return SessionUtil.isLogged("currentUser");
    }
}
