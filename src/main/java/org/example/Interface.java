package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    // JFrame'i burada sınıf seviyesinde tanımlıyoruz
    JFrame frame;

    // Constructor (Yapıcı) metodunda JFrame'i ayarlıyoruz
    public Interface() {
        frame = new JFrame("Online Cargo Tracking System"); // Pencereyi oluşturuyoruz
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Pencere kapandığında uygulama sonlansın
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Tam ekran yapmak için
        frame.setVisible(true);  // Pencereyi görünür yapıyoruz
    }
}