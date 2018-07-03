/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AuctionProductDAO;
import dao.UserDAO;
import db.dto.UserAuthenticationDto;
import entity.AuctionProduct;
import entity.User;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import util.SessionUtil;

/**
 *
 * @author esdra
 */

@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean {
    private User user;
    private ArrayList<AuctionProduct> produtos;
    
    @ManagedProperty(value="#{auctionManagerBean}")
    private AuctionManagerBean auctionManagerBean;

    
    public void retriveUserInformation() {
        try {
            
            this.user = UserDAO.getUser(((UserAuthenticationDto)SessionUtil.getParam("currentUser")).getEmail());
            System.out.println("TESTE USER" + this.user);
        } catch (ParseException | ClassNotFoundException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void placeBid(int productId) {        
        if(user.getCredits() > 0){
            auctionManagerBean.placeBid(user.getEmail(), productId);
            user.setCredits(user.getCredits() - 1);
            try {
                UserDAO.updateUser(user);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public String getUserProducts() {
        try {
            produtos = (ArrayList<AuctionProduct>) AuctionProductDAO.getUserProducts(user.getEmail());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "/userpage.xhtml?faces-redirect=true";
    }
    
    public String pay(int productId) {
        Double credit = this.user.getCash();
        for(AuctionProduct p: produtos) {
            if((productId == p.getId()) && (credit >= p.getCurrentValue())) {
                p.setHasPaid(true);
                this.user.setCash(credit - p.getCurrentValue());
                try {
                    UserDAO.updateUser(user);
                    AuctionProductDAO.updateProduct(p);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        return "/userpage.xhtml?faces-redirect=true";
    }
    
    public ArrayList<AuctionProduct> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<AuctionProduct> produtos) {
        this.produtos = produtos;
    }
    
    public String addCash() {
        user.setCash(user.getCash() + 20.0);
        try {
            UserDAO.updateUser(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/userpage.xhtml?faces-redirect=true";
    }
    
    public String buyCredit(int number) {
        double credit = user.getCash();
        if(number <= credit) {
            user.setCredits(user.getCredits() + number);
            user.setCash(credit - number);
        }
        try {
            UserDAO.updateUser(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/userpage.xhtml?faces-redirect=true";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuctionManagerBean getAuctionManagerBean() {
        return auctionManagerBean;
    }

    public void setAuctionManagerBean(AuctionManagerBean auctionManagerBean) {
        this.auctionManagerBean = auctionManagerBean;
    }
}
