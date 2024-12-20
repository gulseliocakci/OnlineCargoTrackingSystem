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
        panel.setLayout(null);  // null layout kullanıyoruz, bu sayede her butonun konumunu manuel belirleyeceğiz

        // Ekran boyutları
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenWidth = toolkit.getScreenSize().width;   // Gerçek ekran genişliği
        int screenHeight = toolkit.getScreenSize().height; // Gerçek ekran yüksekliği

        // Buton boyutları (Ekranın boyutlarına göre dinamik)
        int buttonWidth = screenWidth / 6;  // Ekranın 1/6'sı kadar genişlik
        int buttonHeight = screenHeight / 8; // Ekranın 1/8'i kadar yükseklik

        // Aradaki boşluklar (Butonlar arasındaki mesafeyi eşitlemek için)
        int horizontalSpacing = (screenWidth - 3 * buttonWidth) / 4; // 3 buton arası
        int verticalSpacing = (screenHeight - 2 * buttonHeight) / 3; // 2 buton arası

        // Butonları oluşturuyoruz
        JButton button1 = new JButton("Yeni Müşteri Oluştur");
        JButton button2 = new JButton("Yeni Kargo Gönder");
        JButton button3 = new JButton("");
        JButton button4 = new JButton("Kargo Geçmişi Sorgula");
        JButton button5 = new JButton("Kargo Durumu Sorgula");

        // Butonları konumlandırıyoruz
        // Üst satırda 3 buton
        button1.setBounds(horizontalSpacing, verticalSpacing, buttonWidth, buttonHeight); // Sol buton
        button2.setBounds((screenWidth - buttonWidth) / 2, verticalSpacing, buttonWidth, buttonHeight); // Ortada buton
        button3.setBounds(screenWidth - horizontalSpacing - buttonWidth, verticalSpacing, buttonWidth, buttonHeight); // Sağ buton

        // Alt satırda 2 buton
        button4.setBounds(horizontalSpacing + (screenWidth - 3 * buttonWidth) / 4, verticalSpacing + buttonHeight + verticalSpacing, buttonWidth, buttonHeight); // Alt sol buton
        button5.setBounds(screenWidth - horizontalSpacing - buttonWidth - (screenWidth - 3 * buttonWidth) / 4, verticalSpacing + buttonHeight + verticalSpacing, buttonWidth, buttonHeight); // Alt sağ buton

        // Her bir butona ActionListener ekliyoruz
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Müşteri ID'sini al
                String customerID = JOptionPane.showInputDialog(frame, "Müşteri ID:");

                // Müşteri ID'sinin boş olmasını engelle ve sadece sayılar kabul et
                while (customerID == null || customerID.trim().isEmpty() || !customerID.matches("[0-9]+")) {
                    if (customerID == null) return; // Eğer kullanıcı 'İptal' butonuna basarsa çık
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir Müşteri ID girin (sadece sayılar).");
                    customerID = JOptionPane.showInputDialog(frame, "Müşteri ID:");
                }

                // Müşteri Adını al
                String customerFirstname = JOptionPane.showInputDialog(frame, "Müşteri Adı:");

                // Müşteri Adı boş olmasın
                while (customerFirstname == null || customerFirstname.trim().isEmpty()) {
                    if (customerFirstname == null) return; // Kullanıcı 'İptal' butonuna basarsa çık
                    JOptionPane.showMessageDialog(frame, "Müşteri Adı boş olamaz.");
                    customerFirstname = JOptionPane.showInputDialog(frame, "Müşteri Adı:");
                }

                // Müşteri Soyadını al
                String customerLastname = JOptionPane.showInputDialog(frame, "Müşteri Soyadı:");

                // Müşteri Soyadı boş olmasın
                while (customerLastname == null || customerLastname.trim().isEmpty()) {
                    if (customerLastname == null) return; // Kullanıcı 'İptal' butonuna basarsa çık
                    JOptionPane.showMessageDialog(frame, "Müşteri Soyadı boş olamaz.");
                    customerLastname = JOptionPane.showInputDialog(frame, "Müşteri Soyadı:");
                }

                // Verileri müşteri listesine ekle
                if (customer.addCustomer(customerID, customerFirstname, customerLastname, frame)) {
                    JOptionPane.showMessageDialog(frame, "Müşteri başarıyla eklendi!");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 2. butona tıklandığında yapılacak işlem
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 3. butona tıklandığında yapılacak işlem
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 4. butona tıklandığında yapılacak işlem
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 5. butona tıklandığında yapılacak işlem
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
