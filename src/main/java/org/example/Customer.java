package org.example;

import java.util.Date;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private CargoNode head;

    public Customer(String customerID, String firstName, String lastName) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.head = null;
    }

    public void addCargoToHistory(Cargo cargo) {
        CargoNode newNode = new CargoNode(cargo);
        if (head == null || head.cargo.getSendDate().after(cargo.getSendDate())) {
            newNode.next = head;
            head = newNode;
        } else {
            CargoNode current = head;
            while (current.next != null && current.next.cargo.getSendDate().before(cargo.getSendDate())) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void printCustomerDetails() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Cargo History: ");
        printHistory();
    }

    private void printHistory() {
        CargoNode current = head;
        while (current != null) {
            System.out.println(current.cargo);
            current = current.next;
        }
    }

    private static class CargoNode {
        Cargo cargo;
        CargoNode next;

        public CargoNode(Cargo cargo) {
            this.cargo = cargo;
            this.next = null;
        }
    }
}
