package org.example;
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        // CustomerManager nesnesi oluşturuluyor
        CustomerManager manager = new CustomerManager();

        // Müşterileri ekliyoruz
        manager.addCustomer(1, "Ali", "Yılmaz");
        manager.addCustomer(2, "Veli", "Demir");
        manager.addCustomer(3, "Ayşe", "Kara");

        // Kargoları belirli müşterilere ekliyoruz
        manager.addCargoToCustomer(1, 101, new Date(1234567890000L), true, 5); // Ali'ye kargo
        manager.addCargoToCustomer(1, 102, new Date(1234567880000L), false, 7); // Ali'ye kargo
        manager.addCargoToCustomer(2, 103, new Date(1234567900000L), true, 3); // Veli'ye kargo
        manager.addCargoToCustomer(2, 104, new Date(1234567860000L), false, 2); // Veli'ye kargo

        // Tüm müşterileri ve kargo geçmişlerini listeleme
        manager.listCustomers();

        // Müşteri ID'sine göre kargo geçmişini sorgulama
        System.out.println("\nSorgulama: Müşteri 1'in Kargo Geçmişi");
        manager.listCargoHistoryForCustomer(1);

        System.out.println("\nSorgulama: Müşteri 2'nin Kargo Geçmişi");
        manager.listCargoHistoryForCustomer(2);
    }
}