package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    JFrame frame;  // JFrame nesnesi
    JPanel panel;  // Butonları yerleştireceğimiz panel
    CustomerManager customerManager = new CustomerManager();
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
        JButton button3 = new JButton("Ağaç Yapısını Göster");
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
                if (customerManager.addCustomer(customerID, customerFirstname, customerLastname, frame)) {
                    JOptionPane.showMessageDialog(frame, "Müşteri başarıyla eklendi!");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kullanıcıdan Müşteri ID'sini alıyoruz
                String customerID = JOptionPane.showInputDialog(frame, "Müşteri ID:");

                // Müşteri ID'sinin boş olmasını engelle ve sadece sayılar kabul et
                while (customerID == null || customerID.trim().isEmpty() || !customerID.matches("[0-9]+")) {
                    if (customerID == null) return; // Eğer kullanıcı 'İptal' butonuna basarsa çık
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir Müşteri ID girin (sadece sayılar).");
                    customerID = JOptionPane.showInputDialog(frame, "Müşteri ID:");
                }

                // Müşteri var mı diye kontrol ediyoruz
                Customer customer = customerManager.findCustomerById(customerID);
                if (customer == null) {
                    JOptionPane.showMessageDialog(frame, "Bu Müşteri ID ile kayıtlı bir müşteri bulunmamaktadır.");
                    return;
                }
                // Şehir ve ilçeleri listele
                String citiesAndDistricts = cargoRoutingTree.getCitiesAndDistricts();
                // Listeyi bir dialog penceresinde göster
                JOptionPane.showMessageDialog(frame, citiesAndDistricts, "Şehirler ve İlçeler", JOptionPane.INFORMATION_MESSAGE);

                // CargoRoutingTree'den şehir ve ilçe bilgileri alınıp kullanıcıya bilgilendirme yapılacak
                String cityName = JOptionPane.showInputDialog(frame, "Şehir Adı:");
                // Şehir kontrolü
                while (cityName == null || cityName.trim().isEmpty() || !cargoRoutingTree.cityExists(cityName)) {
                    if (cityName == null) return;
                    JOptionPane.showMessageDialog(frame, "Geçersiz şehir adı. Lütfen geçerli bir şehir girin.");
                    cityName = JOptionPane.showInputDialog(frame, "Şehir Adı:");
                }

                String districtName = JOptionPane.showInputDialog(frame, "İlçe Adı:");
                // İlçe kontrolü
                while (districtName == null || districtName.trim().isEmpty() || !cargoRoutingTree.districtExists(cityName, districtName)) {
                    if (districtName == null) return;
                    JOptionPane.showMessageDialog(frame, "Geçersiz ilçe adı. Lütfen geçerli bir ilçe girin.");
                    districtName = JOptionPane.showInputDialog(frame, "İlçe Adı:");
                }
                // Şehir ve İlçe, CargoRoutingTree'ye ekleniyor
                cargoRoutingTree.addCity(cityName); // Şehri ekle
                cargoRoutingTree.addDistrict(cityName, districtName); // İlçeyi ekle

                // Kargo ID'sinin benzersiz olup olmadığını kontrol et
                int cargoId = -1;
                boolean isUnique = false;

                while (!isUnique) {
                    String cargoIdInput = JOptionPane.showInputDialog(frame, "Kargo ID:");

                    // Kargo ID'si boş olmamalı ve sadece sayılar kabul edilmeli
                    if (cargoIdInput == null || cargoIdInput.trim().isEmpty() || !cargoIdInput.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir Kargo ID girin (sadece sayılar).");
                    } else {
                        cargoId = Integer.parseInt(cargoIdInput);

                        if (!customerManager.hasCargoWithId(cargoId)) {  // Customer sınıfındaki metodu kullanıyoruz
                            isUnique = true; // ID benzersiz, işlemi sonlandır
                        } else {
                            JOptionPane.showMessageDialog(frame, "Bu Kargo ID'si zaten mevcut. Lütfen farklı bir ID girin.");
                        }
                    }
                }
                Date cargoDate = new Date(); // Kargo tarihi şu anki tarih olacak
                boolean isDelivered = false; // Kargo henüz teslim edilmedi

                // Teslimat süresi hesaplanacak
                int deliveryTime = cargoRoutingTree.calculateDeliveryTime(cityName, districtName);
                if (deliveryTime == -1) {
                    JOptionPane.showMessageDialog(frame, "Teslimat süresi hesaplanamadı.");
                    return;
                }

                // Kargo ekleme işlemi için CustomerManager sınıfındaki addCargoToCustomer metodunu kullanıyoruz
                customerManager.addCargoToCustomer(customerID, cargoId, cargoDate, isDelivered, deliveryTime, frame);

                // Kargo eklendikten sonra kullanıcıya bilgi ver
                JOptionPane.showMessageDialog(frame, "Kargo başarıyla eklendi!\nTeslimat Süresi: " + deliveryTime + " gün");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ağaç yapısını alalım
                StringBuilder treeStructure = new StringBuilder();

                // CargoRoutingTree'deki ağaç yapısını displayTree metodu ile gösteriyoruz
                cargoRoutingTree.displayTree(treeStructure);

                // Eğer ağaç boşsa, kullanıcıya bilgilendirme mesajı gösterelim
                if (treeStructure.toString().trim().isEmpty()) {
                    treeStructure.append("Ağaç yapısına henüz şehir veya ilçe eklenmemiştir.\n");
                }

                // Ağaç yapısını kullanıcıya göstermek için bir JOptionPane kullanıyoruz
                JOptionPane.showMessageDialog(frame, treeStructure.toString(), "Şehirler ve İlçeler", JOptionPane.INFORMATION_MESSAGE);
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
