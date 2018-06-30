/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author esdra
 */

@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean {
    private User user;
    
    @ManagedProperty(value="#{auctionManagerBean}")
    private AuctionManagerBean auctionManagerBean;
    
    
    public void retriveUserInformation() {
        
        // TODO: recuperar informação do banco
        
        this.user = new User("Esdras", "esdraslchaves@gmail.com", 30, 45.0);
    }
    
    public void placeBid(int productId) {
        //TODO: Checar se o usuário possui dinheiro e etc...
        
        auctionManagerBean.placeBid(user.getEmail(), productId);
        
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
