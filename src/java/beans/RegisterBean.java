/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDAO;
import db.dto.UserRegisterDto;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import util.FacesUtil;

/**
 *
 * @author esdra
 */

@RequestScoped
@ManagedBean
public class RegisterBean implements Serializable {
    
    private String name;
    private String email;
    private String password;
    
    public String register() throws ClassNotFoundException {
        
        UserRegisterDto userDto;
        userDto = new UserRegisterDto(name, email, password);
        
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        u.setCredits(50);
        u.setCash(0);
        u.setPassword(password);
        
        UserDAO.saveUser(u);
        
        // TODO: Adicionar lógica do banco (register success)
        if(true) {
            System.out.println("Registro efetuado com sucesso!");
            return "/login.xhtml";
        } else {
            FacesUtil.addErrorMessage("Já existe um usuário para essa conta de email");
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
