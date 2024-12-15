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

    // Kargo ekleme metodunu sıralı şekilde yapacağız
    public void addCargo(Cargo cargo) {
        // LinkedList'ı tarih sırasına göre sıralayarak ekleyeceğiz
        int index = 0;
        while (index < cargos.size() && cargos.get(index).getCargoDate().before(cargo.getCargoDate())) {
            index++;
        }
        cargos.add(index, cargo); // Sıralı şekilde ekle
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + firstName + " " + lastName;
    }
}
