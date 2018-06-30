/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

/**
 *
 * @author Gustavo
 */
public class ProductRegisterDto {
    private String productName;
    private int productId;
    private String productDescription;
    private int productInitialValue;
    private String productInitialDate;
    private String productImage;
    
    public ProductRegisterDto(String productName, int productId, String productDescription, int productInitialValue, String productInitialDate, String productImage) {
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productInitialValue = productInitialValue;
        this.productInitialDate = productInitialDate;
        this.productImage = productImage;
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
}

