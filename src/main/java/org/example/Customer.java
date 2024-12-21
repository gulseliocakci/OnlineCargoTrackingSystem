package org.example;
import java.util.LinkedList;

public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private LinkedList<Cargo> cargos; // Kargo gönderim geçmişi (LinkedList)

    // Constructor
    public Customer(String customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cargos = new LinkedList<>();
    }

    // Getter ve Setter'lar
    public String getCustomerId() {
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

    // Yeni addCargo metodu
    public void addCargo(Cargo cargo) {
        this.cargos.add(cargo);
    }

    // Aynı kargo ID'sine sahip bir kargo var mı kontrolü
    public boolean hasCargoWithId(int cargoId) {
        for (Cargo cargo : cargos) {
            if (cargo.getCargoId() == cargoId) {
                return true; // Eğer aynı ID'ye sahip bir kargo varsa
            }
        }
        return false; // Eğer yoksa
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + firstName + " " + lastName;
    }
}
