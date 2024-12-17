package org.example;
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        /*// CustomerManager oluşturuluyor
        CustomerManager manager = new CustomerManager();

        // Müşteriler ekleniyor
        manager.addCustomer(1, "John", "Doe");
        manager.addCustomer(2, "Jane", "Smith");

        // Kargolar ekleniyor
        manager.addCargoToCustomer(1, 101, new Date(2024 - 1900, 11, 15), false, 3);  // Kargo 3 gün sürecek
        manager.addCargoToCustomer(1, 102, new Date(2024 - 1900, 11, 16), false, 1);  // Kargo 1 gün sürecek
        manager.addCargoToCustomer(2, 103, new Date(2024 - 1900, 11, 14), true, 2);   // Kargo 2 gün sürecek
        manager.addCargoToCustomer(2, 104, new Date(2024 - 1900, 11, 17), false, 4);  // Kargo 4 gün sürecek

        // Kargo geçmişi sorgulama
        System.out.println("Listing cargo history for Customer ID 1:");
        manager.listCargoHistoryForCustomer(1);

        System.out.println("\nListing cargo history for Customer ID 2:");
        manager.listCargoHistoryForCustomer(2);

        // PriorityQueue'daki tüm kargoları listeleme
        System.out.println("\nPriority Queue (Sorted by Delivery Time):");
        manager.listPriorityQueue();

        // Kargo işleme
        System.out.println("\nProcessing the next cargo from priority queue:");
        manager.processNextCargo();

        // Kargo işleme tekrar yapılıyor
        System.out.println("\nProcessing the next cargo from priority queue:");
        manager.processNextCargo();*/
        CargoRoutingTree tree = new CargoRoutingTree();
        tree.showCitiesAndDistricts();
    }
}