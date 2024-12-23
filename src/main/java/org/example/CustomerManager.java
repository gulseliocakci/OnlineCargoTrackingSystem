package org.example;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Date;
import java.util.Comparator;


public class CustomerManager {
    private List<Customer> customers; // Tüm müşterilerin listesi
    private PriorityQueue<Cargo> cargoPriorityQueue; // Kargoların teslimat önceliği ile sıralandığı kuyruk

    // Constructor
    public CustomerManager() {
        this.customers = new ArrayList<>();
        this.cargoPriorityQueue = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.getDeliveryTime(), c2.getDeliveryTime())); // Teslimat süresine göre sıralama
    }

    // Müşteri ekleme
    public boolean addCustomer(String customerId, String firstName, String lastName, JFrame frame) {
        // Müşteri ID'sinin benzersiz olup olmadığını kontrol et
        if (findCustomerById(customerId) != null) {
            // ID zaten varsa, kullanıcıya uyarı ver
            JOptionPane.showMessageDialog(frame, "Bu Müşteri ID zaten var. Lütfen farklı bir ID girin.",
                    "Hata", JOptionPane.ERROR_MESSAGE);
            return false; // ID zaten varsa müşteri eklenmez
        }

        // Yeni müşteri oluşturuluyor
        Customer customer = new Customer(customerId, firstName, lastName);
        customers.add(customer); // Müşteri listeye ekleniyor
        return true; // Müşteri başarıyla eklendi
    }

    // Müşteri ID'sine göre müşteri arama
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null; // Müşteri bulunamazsa null döner
    }

    // Kargo ID'sinin benzersiz olup olmadığını kontrol et
    public boolean hasCargoWithId(int cargoId) {
        // Tüm müşterilerin kargo geçmişi kontrol ediliyor
        for (Customer customer : customers) {
            for (Cargo cargo : customer.getCargos()) {
                if (cargo.getCargoId() == cargoId) {
                    return true; // Eğer aynı ID'ye sahip bir kargo varsa
                }
            }
        }
        return false; // Eğer yoksa
    }

    // Kargo ekleme işlemi
    public void addCargoToCustomer(String customerId, int cargoId, Date cargoDate, String deliveryStatus, int deliveryTime, JFrame frame) {
        // Kargo ID kontrolü (tüm sistemde benzersiz olmalı)
        if (hasCargoWithId(cargoId)) {
            JOptionPane.showMessageDialog(frame, "Bu Kargo ID'si zaten mevcut. Lütfen farklı bir ID girin.",
                    "Hata", JOptionPane.ERROR_MESSAGE);
            return; // Aynı ID'ye sahip kargo varsa, kargo eklenmez
        }

        // Müşteri bulma
        Customer customer = findCustomerById(customerId);

        if (customer != null) {
            // Kargo nesnesini oluşturma
            Cargo cargo = new Cargo(cargoId, cargoDate, "İşleme Alındı", deliveryTime);

            // Kargo nesnesini müşteriye ekleme
            customer.addCargo(cargo); // Müşteriye kargo ekleniyor

            // Kargoyu PriorityQueue'ya ekleme (Teslimat süresine göre sıralı eklenir)
            cargoPriorityQueue.add(cargo); // PriorityQueue'da teslimat süresine göre sıralama yapılır
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
        }
    }

    // Müşteri ID'sine göre kargo geçmişini sorgulama
    public void listCargoHistoryForCustomer(String customerId, JFrame frame) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            StringBuilder cargoHistory = new StringBuilder();
            cargoHistory.append("Kargo Geçmişi (Müşteri ID: ").append(customerId).append("):\n");
            for (Cargo cargo : customer.getCargos()) {
                cargoHistory.append(cargo).append("\n");
            }
            // Kargo geçmişini bir dialog penceresinde göster
            JOptionPane.showMessageDialog(frame, cargoHistory.toString(), "Kargo Geçmişi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Müşteri ID bulunamadı: " + customerId, "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Müşterileri listeleme
    public void listCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
            for (Cargo cargo : customer.getCargos()) {
                System.out.println(cargo);
            }
        }
    }
    public org.example.Cargo findCargoById(int cargoId) {
        for (org.example.Customer customer : customers) {
            List<org.example.Cargo> cargos = customer.getCargos();

            // Kargo listesini cargoId'ye göre sıralıyoruz
            cargos.sort(Comparator.comparingInt(org.example.Cargo::getCargoId));

            // Binary search algoritmasını uyguluyoruz
            int left = 0;
            int right = cargos.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2; // Orta noktayı hesapla
                org.example.Cargo midCargo = cargos.get(mid);

                if (midCargo.getCargoId() == cargoId) {
                    return midCargo; // Aranan kargo bulundu
                } else if (midCargo.getCargoId() < cargoId) {
                    left = mid + 1; // Sağ yarıda ara
                } else {
                    right = mid - 1; // Sol yarıda ara
                }
            }
        }

        return null; // Kargo bulunamazsa
    }


    public List<Cargo> getAllCargos() {
        List<Cargo> allCargos = new ArrayList<>();
        // Tüm müşterilerdeki kargo listelerini alıyoruz
        for (Customer customer : customers)  {
            allCargos.addAll(customer.getCargos());  // Müşterinin kargo listesine ekliyoruz
        }
        return allCargos;
    }



    // PriorityQueue'daki tüm kargoları listeleme
    public void listPriorityQueue() {
        System.out.println("Priority Queue (Sorted by Delivery Time):");
        for (Cargo cargo : cargoPriorityQueue) {
            System.out.println(cargo);
        }
    }
}
