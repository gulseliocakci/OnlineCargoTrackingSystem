package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private CargoNode head;

    // Static map to hold all customers
    private static Map<String, Customer> customers = new HashMap<>();

    public Customer(String customerID, String firstName, String lastName) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.head = null;
    }

    // Static method to add customer to the system
    public static void addCustomer(String customerID, String firstName, String lastName) {
        if (customers.containsKey(customerID)) {
            System.out.println("Bu müşteri zaten mevcut.");
        } else {
            Customer customer = new Customer(customerID, firstName, lastName);
            customers.put(customerID, customer);
            System.out.println("Yeni müşteri başarıyla eklendi!");
        }
    }

    // Static method to get customer by ID
    public static Customer getCustomerByID(String customerID) {
        return customers.get(customerID);
    }

    // Add cargo to a customer's history
    public void addCargoToHistory() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kargo ID: ");
        String cargoID = scanner.nextLine();

        // Get send date
        System.out.print("Gönderim Tarihi (yyyy-MM-dd formatında): ");
        String dateString = scanner.nextLine();
        Date sendDate = parseDate(dateString);

        System.out.print("Teslim Durumu (true = teslim edildi, false = teslim edilmedi): ");
        boolean isDelivered = scanner.nextBoolean();

        System.out.print("Teslim Süresi (gün cinsinden): ");
        int deliveryTime = scanner.nextInt();

        // Create CargoNode object and add to history
        CargoNode newNode = new CargoNode(cargoID, sendDate, isDelivered, deliveryTime);
        addCargoToHistory(newNode);
    }

    // Add cargo to the history (sorted by send date)
    private void addCargoToHistory(CargoNode cargoNode) {
        if (head == null || head.cargo.getSendDate().after(cargoNode.cargo.getSendDate())) {
            cargoNode.next = head;
            head = cargoNode;
        } else {
            CargoNode current = head;
            while (current.next != null && current.next.cargo.getSendDate().before(cargoNode.cargo.getSendDate())) {
                current = current.next;
            }
            cargoNode.next = current.next;
            current.next = cargoNode;
        }
    }

    // Print customer details
    public void printCustomerDetails() {
        System.out.println("\nMüşteri ID: " + customerID);
        System.out.println("İsim: " + firstName + " " + lastName);
        System.out.println("Kargo Geçmişi:");
        printHistory();
    }

    // Print cargo history
    private void printHistory() {
        CargoNode current = head;
        if (current == null) {
            System.out.println("Hiç kargo geçmişi bulunmamaktadır.");
        } else {
            while (current != null) {
                System.out.println(current.cargo);
                current = current.next;
            }
        }
    }

    // Parse date helper function
    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            System.out.println("Geçersiz tarih formatı!");
            return new Date();  // If invalid, return current date
        }
    }

    // Nested CargoNode class to represent cargo history
    private static class CargoNode {
        String cargoID;
        Date sendDate;
        boolean isDelivered;
        int deliveryTime;
        CargoNode next;

        public CargoNode(String cargoID, Date sendDate, boolean isDelivered, int deliveryTime) {
            this.cargoID = cargoID;
            this.sendDate = sendDate;
            this.isDelivered = isDelivered;
            this.deliveryTime = deliveryTime;
            this.next = null;
        }

        @Override
        public String toString() {
            return "KargoID: " + cargoID + ", Gönderim Tarihi: " + sendDate + ", Teslim Durumu: " + (isDelivered ? "Teslim Edildi" : "Teslim Edilmedi") + ", Teslim Süresi: " + deliveryTime + " gün";
        }
    }

    // List all customers
    public static void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Hiç müşteri yok.");
        } else {
            for (String customerID : customers.keySet()) {
                System.out.println("Müşteri ID: " + customerID);
            }
        }
    }
}
