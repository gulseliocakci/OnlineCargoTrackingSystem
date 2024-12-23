package org.example;
import java.util.Date;

public class Cargo implements Comparable<Cargo> {
    private int cargoId; // Kargo ID
    private Date cargoDate; // Kargo tarihi
    private String deliveryStatus; // Teslim durumu (true: teslim edildi, false: teslim edilmedi)
    private int deliveryTime; // Teslim süresi (gün cinsinden)

    // Constructor
    public Cargo(int cargoId, Date cargoDate, String deliveryStatus, int deliveryTime) {
        this.cargoId = cargoId;
        this.cargoDate = cargoDate;
        this.deliveryStatus = deliveryStatus;
        this.deliveryTime = deliveryTime;
    }

    // Getter ve Setter'lar
    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public Date getCargoDate() {
        return cargoDate;
    }

    public void setCargoDate(Date cargoDate) {
        this.cargoDate = cargoDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    // Teslimat süresine göre karşılaştırma (daha kısa teslimat süresi olan önceliklidir)
    @Override
    public int compareTo(Cargo other) {
        return Integer.compare(this.deliveryTime, other.deliveryTime);
    }

    @Override
    public String toString() {
        return "Cargo ID: " + cargoId + ", Date: " + cargoDate + ", Status: " + deliveryStatus + ", Delivery Time: " + deliveryTime;
    }
}
