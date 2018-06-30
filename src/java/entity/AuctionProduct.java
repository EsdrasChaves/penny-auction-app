/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Timer;
import java.util.TimerTask;

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
    private int expirationTime;
    private Timer timer;
    
    private static final int DEFAULT_TIMER = 40;
    
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
        return initialValue + currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getUserName() {
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
    
    private void finishAuction() {
        setHasFinished(true);
        timer.cancel();
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
