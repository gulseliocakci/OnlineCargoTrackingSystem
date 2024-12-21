package org.example;
import java.util.Date;

public class Cargo implements Comparable<Cargo> {
    private int cargoId; // Kargo ID
    private Date cargoDate; // Kargo tarihi
    private boolean isDelivered; // Teslim durumu (true: teslim edildi, false: teslim edilmedi)
    private int deliveryTime; // Teslim süresi (gün cinsinden)

    // Constructor
    public Cargo(int cargoId, Date cargoDate, boolean isDelivered, int deliveryTime) {
        this.cargoId = cargoId;
        this.cargoDate = cargoDate;
        this.isDelivered = isDelivered;
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

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
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
        return "Cargo ID: " + cargoId + ", Date: " + cargoDate +
                ", Delivered: " + isDelivered + ", Delivery Time: " + deliveryTime + " days";
    }

    // Kargo ID'si eşit olup olmadığını kontrol etme (benzersiz kargo ID kontrolü için kullanılır)
    public boolean hasSameId(int cargoId) {
        return this.cargoId == cargoId;
    }
}
