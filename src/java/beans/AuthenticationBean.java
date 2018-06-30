/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDAO;
import db.dto.UserAuthenticationDto;
import entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import util.FacesUtil;
import util.SessionUtil;

/**
 *
 * @author esdra
 */

@RequestScoped
@ManagedBean
public class AuthenticationBean implements Serializable {
    private String email;
    private String password;

    @ManagedProperty(value="#{userBean}")
    private UserBean userInfoBean;
    
    public String login () throws ClassNotFoundException, ParseException {
        
        
        UserAuthenticationDto userDto;
        userDto = new UserAuthenticationDto(email, password);
        
        User u = UserDAO.getUser(email);
       
        if(u.getPassword().equals(password)) {
            System.out.println("Sucesso na autenticação");
            
            SessionUtil.setParam("currentUser", userDto);
            
            userInfoBean.retriveUserInformation();
            
            return "/home.xhtml?faces-redirect=true";
        } else if(email.equals("admin") && password.equals("admin")){
            return "/adminpage.xhtml?faces-redirect=true";
        }else {
            FacesUtil.addErrorMessage("Usuário nao encontrado");
            return null;
        }
        
    }
    
    public String logout() {
        SessionUtil.remove("currentUser");
        SessionUtil.invalidate();
        return "/home.xhtml?faces-redirect=true";
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
    
    
}
