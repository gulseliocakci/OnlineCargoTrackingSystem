package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    JFrame frame;  // JFrame nesnesi
    JPanel panel;  // Butonları yerleştireceğimiz panel
    CustomerManager customer = new CustomerManager();
    CargoRoutingTree cargoRoutingTree = new CargoRoutingTree();
    //Cargo cargo = new Cargo();


    // Constructor (Yapıcı) metodunda JFrame'i ayarlıyoruz
    public Interface() {
        frame = new JFrame("Online Cargo Tracking System"); // Pencereyi oluşturuyoruz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Pencere kapandığında uygulama sonlansın
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Tam ekran yapmak için

        // Panel oluşturuyoruz
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));  // 5 satırlık bir grid düzeni

        // Butonları oluşturuyoruz
        JButton button1 = new JButton("Buton 1");
        JButton button2 = new JButton("Buton 2");
        JButton button3 = new JButton("Buton 3");
        JButton button4 = new JButton("Buton 4");
        JButton button5 = new JButton("Buton 5");

        // Her bir butona ActionListener ekliyoruz
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kullanıcıdan 3 veri alıyoruz
                String customerID = JOptionPane.showInputDialog(frame, "Müşteri ID:");
                String customerFirstname = JOptionPane.showInputDialog(frame, "Müşteri Adı:");
                String customerLastname = JOptionPane.showInputDialog(frame, "Müşteri Soyadı:");

                customer.addCustomer(customerID, customerFirstname, customerLastname);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Butonları panel'e ekliyoruz
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        // Panel'i frame'e ekliyoruz
        frame.add(panel);

        frame.setVisible(true);  // Pencereyi görünür yapıyoruz
    }
}