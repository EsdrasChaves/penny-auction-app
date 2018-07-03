/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.AuctionProductDAO;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esdra
 */
public class AuctionProduct {
    private int id;
    private String name;
    private String description;
    private int initialValue;
    private int currentValue;
    private String userName;
    private String initialDate;
    private String productImage;
    private boolean hasStarted;
    private boolean hasFinished;
    private boolean hasPaid;
    private int expirationTime;
    private Timer timer;
    private String imageLink;
    
    private static final int DEFAULT_TIMER = 30;
    
    public AuctionProduct() {
    }

    public AuctionProduct(int id, String name, String description, int initialValue, int currentValue, String userName, String initialDate, String productImage, boolean hasStarted, boolean hasFinished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.userName = userName;
        this.initialDate = initialDate;
        this.productImage = productImage;
        this.hasStarted = hasStarted;
        this.hasFinished = hasFinished;
        this.hasPaid = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getUserName() {
        if(this.userName == null)
            return "Ninguém deu um lance";
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
    
    

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public void startAuction() {
        setHasStarted(true);
        setExpirationTime(DEFAULT_TIMER);
        startTimer();
    }
    
    public void finishAuction() {
        setHasFinished(true);
        if(timer != null) {
            timer.cancel();
            try {
                AuctionProductDAO.updateProduct(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AuctionProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    public void placeBid(String userName) {
        if(isHasStarted() && !isHasFinished()) {
            setUserName(userName);
            setCurrentValue(this.currentValue + 1);
            restartTimer();
        }
    }
    
    private void restartTimer() {
        timer.cancel();
        setExpirationTime(DEFAULT_TIMER);
        startTimer();
    }
    
    private void startTimer() {
        this.timer = new Timer();
        timer.schedule(new CounterTimer(), 0, 1000);
    }
    
    class CounterTimer extends TimerTask {

        @Override
        public void run() {
            int currentTime = getExpirationTime();
            currentTime--;
            setExpirationTime(currentTime);
            
            if(currentTime == 0)
                finishAuction();
        }
        
    }
    
}
