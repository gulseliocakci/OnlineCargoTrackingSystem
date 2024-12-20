package org.example;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Interface();
        /*/ CustomerManager oluşturuluyor
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
        manager.processNextCargo();

        CargoRoutingTree routingTree = new CargoRoutingTree();
        Scanner scanner = new Scanner(System.in);

        routingTree.showCitiesAndDistricts();

        while (true) {
            System.out.println("\nYeni bir kargo adresi eklemek için şehir ve ilçe bilgisi girin.");
            System.out.print("Şehir (çıkmak için 'exit'): ");
            String city = scanner.nextLine();
            if (city.equalsIgnoreCase("exit")) break;

            System.out.print("İlçe: ");
            String district = scanner.nextLine();

            // Ağaca adres ekleme
            try {
                routingTree.addCity(city);
                routingTree.addDistrict(city, district);

                // Teslim süresini hesaplama
                int deliveryTime = routingTree.calculateDeliveryTime(city, district);
                System.out.println("Teslim süresi: " + deliveryTime + " gün.");
            } catch (IllegalArgumentException e) {
                System.out.println("Hata: " + e.getMessage());
            }

            // Ağacı yazdırma
            System.out.println("\nGüncel Kargo Ağı:");
            routingTree.displayTree();
        }

        System.out.println("Kargo işlemi tamamlandı.");*/


    }
}