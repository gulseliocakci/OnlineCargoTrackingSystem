package org.example;
import java.util.LinkedList;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private LinkedList<Cargo> cargos; // Kargo gönderim geçmişi (LinkedList)

    // Constructor
    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cargos = new LinkedList<>();
    }

    // Getter ve Setter'lar
    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LinkedList<Cargo> getCargos() {
        return cargos;
    }
    

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + firstName + " " + lastName;
    }
}
