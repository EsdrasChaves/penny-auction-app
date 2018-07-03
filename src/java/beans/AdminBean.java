/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AuctionProductDAO;
import db.dto.ProductRegisterDto;
import entity.AuctionProduct;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import util.FacesUtil;

/**
 *
 * @author esdra
 */

@RequestScoped
@ManagedBean
public class AdminBean implements Serializable {
    
    private String productName;
    private int productId;
    private String productDescription;
    private int productInitialValue;
    private String productInitialDate;
    private String productImage;
    
    @ManagedProperty(value="#{auctionManagerBean}")
    private AuctionManagerBean auctionManagerBean;
    
    public String register() {
        
        ProductRegisterDto productDto;
        productDto = new ProductRegisterDto(productName, productId, productDescription, productInitialValue, productInitialDate, productImage);
        
        AuctionProduct ap = new AuctionProduct(productId,productName,productDescription, productInitialValue, productInitialValue, "", productInitialDate, productImage, false, false);

        try {
            AuctionProductDAO.saveProduct(ap);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(true) {
            System.out.println("Registro efetuado com sucesso!");
            auctionManagerBean.addProduct(ap);
            return "/adminpage.xhtml?faces-redirect=true";
        } else {
            FacesUtil.addErrorMessage("Já existe um produto para esse ID");
            return null;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public int getProductInitialValue() {
        return productInitialValue;
    }

    public void setProductInitialValue(int productInitialValue) {
        this.productInitialValue = productInitialValue;
    }
    
    public String getProductInitialDate() {
        return productInitialDate;
    }
    
    public void setProductInitialDate(String productInitialDate) {
        this.productInitialDate = productInitialDate;
    }
    
    public String getProductImage() {
        return productImage;
    }
    
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    public AuctionManagerBean getAuctionManagerBean(){
        return auctionManagerBean;
    }
    
    public void setAuctionManagerBean(AuctionManagerBean auctionManagerBean){
        this.auctionManagerBean = auctionManagerBean;
    }
}

