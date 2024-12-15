package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Date;

public class CustomerManager {
    private List<Customer> customers; // Tüm müşterilerin listesi
    private PriorityQueue<Cargo> cargoPriorityQueue; // Kargoların teslimat önceliği ile sıralandığı kuyruk

    // Constructor
    public CustomerManager() {
        this.customers = new ArrayList<>();
        this.cargoPriorityQueue = new PriorityQueue<>(); // Teslimat süresine göre sıralanmış kargo kuyruğu
    }

    // Müşteri ekleme
    public void addCustomer(int customerId, String firstName, String lastName) {
        Customer customer = new Customer(customerId, firstName, lastName);
        customers.add(customer);
    }

    // Müşteri ID'sine göre müşteri arama
    public Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null; // Müşteri bulunamazsa null döner
    }

    // Kargo ekleme ve Priority Queue'ya ekleme
    public void addCargoToPriorityQueue(int cargoId, Date cargoDate, boolean isDelivered, int deliveryTime) {
        Cargo cargo = new Cargo(cargoId, cargoDate, isDelivered, deliveryTime);
        cargoPriorityQueue.add(cargo);
    }

    // Kargo işleme
    public void processNextCargo() {
        if (!cargoPriorityQueue.isEmpty()) {
            Cargo cargo = cargoPriorityQueue.poll(); // En öncelikli kargo (en kısa teslimat süresi) alınır
            System.out.println("Processing Cargo: " + cargo);
            // Kargo işleme işlemleri (teslimat durumu güncellenebilir)
        } else {
            System.out.println("No cargo to process.");
        }
    }

    // Müşteri ID'sine göre kargo geçmişini sorgulama
    public void listCargoHistoryForCustomer(int customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            System.out.println("Cargo History for Customer ID: " + customerId);
            for (Cargo cargo : customer.getCargos()) {
                System.out.println(cargo);
            }
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
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

    // PriorityQueue'daki tüm kargoları listeleme
    public void listPriorityQueue() {
        System.out.println("Priority Queue (Sorted by Delivery Time):");
        for (Cargo cargo : cargoPriorityQueue) {
            System.out.println(cargo);
        }
    }
}

