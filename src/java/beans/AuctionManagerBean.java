/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AuctionProduct;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author esdra
 */

@ManagedBean(name = "auctionManagerBean")
@ApplicationScoped
public class AuctionManagerBean {
    private List<AuctionProduct> products;

    public AuctionManagerBean() {
        this.products = new ArrayList<>();
        
        loadProducts();
        
        for(AuctionProduct p: this.products){
            p.startAuction();
        }
    }

    public List<AuctionProduct> getProducts() {
        return products;
    }

    public void setProducts(List<AuctionProduct> products) {
        this.products = products;
    }
    
    public void placeBid(String userId, int productId) {
        AuctionProduct product = getProduct(productId);
        
        if(product != null) {
            if(product.isHasStarted() && !product.isHasFinished()) {
                product.placeBid(userId);
            }
        }
    }
    
    public int getProductTimer(int productId) {
        AuctionProduct product = getProduct(productId);
        
        if(product != null) {
            return product.getExpirationTime();
        }
        
        return 0;
    }
    
    private AuctionProduct getProduct(int id) {
        for(AuctionProduct p: this.products){
            if(p.getId() == id)
                return p;
        }
        return null;
    }
    
    public void loadProducts() {
        this.products.clear();
        
        // TODO: Adicionar busca no banco
        
        this.products.add(new AuctionProduct(0, "Iphone X", "Novo IPhone X", 0, 0, "", "30-05-2018", "", true, true,"https://www.feiradosimportados.com.br/wp-content/uploads/2017/11/iphone-x-min.jpg"));
        this.products.add(new AuctionProduct(1, "PowerBank", "3.000 mAh", 0, 0, "", "30-05-2019", "", false, false, "http://cdn.shopify.com/s/files/1/1466/6758/products/51uaaL5NWvL._SL1200_600x.jpg?v=1500282045"));
        this.products.add(new AuctionProduct(2, "NotebookAsus", "Notebook Gamer", 0, 0, "", "10-10-2010", "", true, false, "http://appsisecommerces3.s3.amazonaws.com/clientes/cliente5997/produtos/29183/Z01451308029.jpg"));
        this.products.add(new AuctionProduct(3, "Caloi 10", "Bicicleta Speed", 50, 0, "", "30-05-2018", "", false, false, "https://mthumbs.buscape.com.br/bicicleta/caloi-10-aro-700_600x600-PU2eace_1.jpg"));
        this.products.add(new AuctionProduct(4, "Les Paul", "Guitarra", 0, 0, "", "13-10-2015", "", true, true, "https://images.musicstore.de/images/0960/epiphone-les-paul-standard-eb-negro_1_GIT0002826-001.jpg"));
        this.products.add(new AuctionProduct(5, "Conjunto Panela", "Panelas Inox", 0, 0, "", "30-05-2018", "", false, false, "http://i.mlcdn.com.br/1500x1500/conjunto-de-panelas-tramontina-turim6-pecas-215930600.jpg"));
    }
    
    
    
    
}
