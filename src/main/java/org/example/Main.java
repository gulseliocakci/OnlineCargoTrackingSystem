package org.example;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // CargoManager nesnesi oluşturuluyor
        CargoManager cargoManager = new CargoManager();

        // Teslim edilmiş kargolar ekleniyor
        cargoManager.addDeliveredCargo(new Cargo("C001", new Date(), true, 5));
        cargoManager.addDeliveredCargo(new Cargo("C003", new Date(), true, 3));
        cargoManager.addDeliveredCargo(new Cargo("C002", new Date(), true, 7));

        // Teslim edilmemiş kargolar ekleniyor
        cargoManager.addUndeliveredCargo(new Cargo("C004", new Date(), false, 10));
        cargoManager.addUndeliveredCargo(new Cargo("C005", new Date(), false, 2));
        cargoManager.addUndeliveredCargo(new Cargo("C006", new Date(), false, 5));

        // Teslim edilmiş kargoları binary search ile arıyoruz
        System.out.println("Binary Search ile Kargo Arama:");
        Cargo searchResult = cargoManager.binarySearch("C002");
        if (searchResult != null) {
            System.out.println("Kargo Bulundu: " + searchResult);
        } else {
            System.out.println("Kargo Bulunamadı");
        }

        // Teslim edilmemiş kargoları sıralıyoruz
        cargoManager.sortUndeliveredCargo();

        System.out.println("\nTeslim Edilmemiş Kargolar Sıralandı:");
        cargoManager.printUndeliveredCargoList();

        System.out.println("\nTeslim Edilmiş Kargolar:");
        cargoManager.printDeliveredCargoList();
    }
}