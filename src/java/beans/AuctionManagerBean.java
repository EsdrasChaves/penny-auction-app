/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.AuctionProduct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    Timer timer;

    public AuctionManagerBean() {
        this.products = new ArrayList<>();
        
        loadProducts();
        aditionalFunc();
        startTimer();
    }

    public List<AuctionProduct> getProducts() {
        return products;
    }

    public void setProducts(List<AuctionProduct> products) {
        this.products = products;
    }
    
    private void aditionalFunc() {
        for(AuctionProduct p: this.products) {
            if(p.isHasStarted() && p.getExpirationTime() == 0)
                p.finishAuction();
        }
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
    
    private void restartTimer() {
        timer.cancel();
        startTimer();
    }
    
    private void startTimer() {
        this.timer = new Timer();
        timer.schedule(new CounterTimer(), 0, 1000);
    }
    
    class CounterTimer extends TimerTask {
        @Override
        public void run() {
            checkAuctionInit();
        }
    }
    
    private void checkAuctionInit() {
        
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date dateInit = null;
        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        for(AuctionProduct p: this.products) {
            try {
                dateInit = format.parse(p.getInitialDate());
            } catch (ParseException ex) {
                Logger.getLogger(AuctionManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Date currentDate = c.getTime();
            
            if(dateInit.equals(currentDate) && !p.isHasFinished() && !p.isHasStarted()) {
                p.startAuction();
            }
        }
    }
    
    private void loadProducts() {
        this.products.clear();
        
        // TODO: Adicionar busca no banco
        
        this.products.add(new AuctionProduct(0, "Iphone X", "Novo IPhone X", 0, 0, "", "30-05-2018", "", true, true));
        this.products.add(new AuctionProduct(1, "PowerBank", "3.000 mAh", 0, 0, "", "30-05-2019", "", false, false));
        this.products.add(new AuctionProduct(2, "NotebookAsus", "Notebook Gamer", 0, 0, "", "10-10-2010", "", true, false));
        this.products.add(new AuctionProduct(3, "Caloi 10", "Bicicleta Speed", 50, 0, "", "30-06-2018", "", false, false));
        this.products.add(new AuctionProduct(4, "Les Paul", "Guitarra", 0, 0, "", "13-10-2015", "", true, true));
        this.products.add(new AuctionProduct(5, "Conjunto Panela", "Panelas Inox", 0, 0, "", "30-05-2018", "", false, false));
    }   
}
