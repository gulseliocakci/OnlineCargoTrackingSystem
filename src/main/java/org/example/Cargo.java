package org.example;

import java.util.Date;

public class Cargo {
    private String cargoID;
    private Date sendDate;
    private boolean isDelivered;
    private int deliveryTime;

    public Cargo(String cargoID, Date sendDate, boolean isDelivered, int deliveryTime) {
        this.cargoID = cargoID;
        this.sendDate = sendDate;
        this.isDelivered = isDelivered;
        this.deliveryTime = deliveryTime;
    }

    public String getCargoID() {
        return cargoID;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    @Override
    public String toString() {
        return "CargoID: " + cargoID + ", Send Date: " + sendDate + ", Delivered: " + isDelivered + ", Delivery Time: " + deliveryTime + " days";
    }
}
