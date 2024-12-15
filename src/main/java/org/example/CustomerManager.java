package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class CustomerManager {
    private List<Customer> customers; // Tüm müşterilerin listesi

    // Constructor
    public CustomerManager() {
        this.customers = new ArrayList<>();
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

    // Kargo ekleme
    public void addCargoToCustomer(int customerId, int cargoId, Date cargoDate, boolean isDelivered, int deliveryTime) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            Cargo cargo = new Cargo(cargoId, cargoDate, isDelivered, deliveryTime);
            customer.addCargo(cargo);
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
}
