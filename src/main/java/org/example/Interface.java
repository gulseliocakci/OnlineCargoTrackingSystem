package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
    JFrame frame;  // JFrame nesnesi
    JPanel panel;  // Butonları yerleştireceğimiz panel
    CustomerManager customerManager = new CustomerManager();
    CargoRoutingTree cargoRoutingTree = new CargoRoutingTree();
    Stack<Cargo> cargoStack = new Stack<>();
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
        JButton button3 = new JButton("Tüm Kargoları Göster");
        JButton button4 = new JButton("Kargo Geçmişi Sorgula");
        JButton button5 = new JButton("Kargo Durumu Sorgula");
        JButton button6 = new JButton("Kargo Durumu Güncelle");
        JButton button7 = new JButton("Ağaç Yapısını Göster");  // Ortada olacak yeni buton

        // 3x3 Grid düzeni, 7. buton tam ortada olacak
        // 1. satır (üstteki satır) - Buton 1, 2, 3
        button1.setBounds(horizontalSpacing, verticalSpacing, buttonWidth, buttonHeight); // Sol buton
        button2.setBounds((screenWidth - buttonWidth) / 2, verticalSpacing, buttonWidth, buttonHeight); // Ortada buton
        button3.setBounds(screenWidth - horizontalSpacing - buttonWidth, verticalSpacing, buttonWidth, buttonHeight); // Sağ buton

        // 2. satır (orta satır) - Buton 4, 5, 6
        button4.setBounds(horizontalSpacing, verticalSpacing + buttonHeight + verticalSpacing, buttonWidth, buttonHeight); // Alt sol buton
        button5.setBounds((screenWidth - buttonWidth) / 2, verticalSpacing + buttonHeight + verticalSpacing, buttonWidth, buttonHeight); // Alt ortada buton
        button6.setBounds(screenWidth - horizontalSpacing - buttonWidth, verticalSpacing + buttonHeight + verticalSpacing, buttonWidth, buttonHeight); // Alt sağ buton

        // 3. satır (alt satır) - 7. buton ekranın tam ortasında
        button7.setBounds((screenWidth - buttonWidth) / 2, (screenHeight - buttonHeight) / 2, buttonWidth, buttonHeight); // Ekranın tam ortasında

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
                while (cityName == null||  cityName.trim().isEmpty() || !cargoRoutingTree.cityExists(cityName)) {
                    if (cityName == null) return;
                    JOptionPane.showMessageDialog(frame, "Geçersiz şehir adı. Lütfen geçerli bir şehir girin.");
                    cityName = JOptionPane.showInputDialog(frame, "Şehir Adı:");
                }

                String districtName = JOptionPane.showInputDialog(frame, "İlçe Adı:");
                // İlçe kontrolü
                while (districtName == null||  districtName.trim().isEmpty() || !cargoRoutingTree.districtExists(cityName, districtName)) {
                    if (districtName == null) return;
                    JOptionPane.showMessageDialog(frame, "Geçersiz ilçe adı. Lütfen geçerli bir ilçe girin.");
                    districtName = JOptionPane.showInputDialog(frame, "İlçe Adı:");
                }

                // Kargo ID'sinin benzersiz olup olmadığını kontrol et
                int cargoId = -1;
                boolean isUnique = false;while (!isUnique) {
                    String cargoIdInput = JOptionPane.showInputDialog(frame, "Kargo ID:");

                    // Kargo ID'si boş olmamalı ve sadece sayılar kabul edilmeli
                    if (cargoIdInput == null||  cargoIdInput.trim().isEmpty() || !cargoIdInput.matches("[0-9]+")) {
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



                // Kargo objesini oluştur ve yığına ekle
                Cargo cargo = new Cargo(cargoId, cargoDate, false, deliveryTime);
                cargoStack.push(cargo);

                // Yığına 5'ten fazla kargo eklenirse en eski kargo çıkarılacak
                if (cargoStack.size() > 5) {
                    cargoStack.pop();
                }


                // Kargo eklendikten sonra kullanıcıya bilgi ver
                JOptionPane.showMessageDialog(frame, "Kargo başarıyla eklendi!\nTeslimat Süresi: " + deliveryTime + " gün");
            }
        });



        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sistemdeki tüm kargoları alıyoruz
                StringBuilder allCargoDetails = new StringBuilder();

                // CustomerManager'dan tüm kargoları alıyoruz
                List<Cargo> allCargos = customerManager.getAllCargos();

                // Eğer kargo yoksa, kullanıcıya bilgi vereceğiz
                if (allCargos.isEmpty()) {
                    allCargoDetails.append("Şu anda sistemde hiçbir kargo bulunmamaktadır.\n");
                } else {
                    // Kargo bilgilerini listeye ekliyoruz
                    for (Cargo cargo : allCargos) {
                        allCargoDetails.append("Kargo ID: ").append(cargo.getCargoId()).append("\n")
                                .append("Teslimat Durumu: ").append(cargo.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi").append("\n")
                                .append("Teslimat Süresi: ").append(cargo.getDeliveryTime()).append(" gün").append("\n")
                                .append("Kargo Tarihi: ").append(cargo.getCargoDate()).append("\n")
                                .append("--------------------------------------\n");
                    }
                }

                // Kargo bilgilerini bir dialog penceresinde gösteriyoruz
                JOptionPane.showMessageDialog(frame, allCargoDetails.toString(), "Tüm Kargolar", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder last5CargoDetails = new StringBuilder();

                // Yığındaki son 5 kargoyu al
                for (Cargo cargo : cargoStack) {
                    last5CargoDetails.append("Kargo ID: ").append(cargo.getCargoId()).append("\n")
                            .append("Teslimat Durumu: ").append(cargo.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi").append("\n")
                            .append("Teslimat Süresi: ").append(cargo.getDeliveryTime()).append(" gün").append("\n")
                            .append("Kargo Tarihi: ").append(cargo.getCargoDate()).append("\n")
                            .append("--------------------------------------\n");
                }

                // Kargo bilgilerini bir dialog penceresinde gösteriyoruz
                JOptionPane.showMessageDialog(frame, last5CargoDetails.toString(), "Son 5 Kargo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kargo ID'sini alıyoruz
                String cargoIDInput = JOptionPane.showInputDialog(frame, "Kargo ID:");

                // Kargo ID'sinin boş olmasını engelle ve sadece sayılar kabul et
                while (cargoIDInput == null || cargoIDInput.trim().isEmpty() || !cargoIDInput.matches("[0-9]+")) {
                    if (cargoIDInput == null) return; // Eğer kullanıcı 'İptal' butonuna basarsa çık
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir Kargo ID girin (sadece sayılar).");
                    cargoIDInput = JOptionPane.showInputDialog(frame, "Kargo ID:");
                }

                int cargoID = Integer.parseInt(cargoIDInput);

                // Kargo ID'sini kullanarak kargo bilgilerini buluyoruz
                Cargo cargo = customerManager.findCargoById(cargoID);

                if (cargo == null) {
                    JOptionPane.showMessageDialog(frame, "Bu Kargo ID'sine ait bir kargo bulunamadı.");
                    return;
                }

                // Kargo bilgilerini göstermek için bir StringBuilder kullanabiliriz
                StringBuilder cargoDetails = new StringBuilder();
                cargoDetails.append("Kargo ID: ").append(cargo.getCargoId()).append("\n")
                        .append("Teslimat Durumu: ").append(cargo.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi").append("\n")
                        .append("Teslimat Süresi: ").append(cargo.getDeliveryTime()).append(" gün").append("\n")
                        .append("Kargo Tarihi: ").append(cargo.getCargoDate()).append("\n");

                // Kargo bilgilerini kullanıcıya göster
                JOptionPane.showMessageDialog(frame, cargoDetails.toString(), "Kargo Bilgisi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ağaç yapısını alalım
                StringBuilder treeStructure = new StringBuilder();

                // CargoRoutingTree'deki ağaç yapısını displayTree metodu ile gösteriyoruz
                cargoRoutingTree.displayTree(treeStructure);

                // Ağaç yapısını kullanıcıya göstermek için bir JOptionPane kullanıyoruz
                JOptionPane.showMessageDialog(frame, treeStructure.toString(), "Şehirler ve İlçeler", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        // Butonları panel'e ekliyoruz
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);

        // Panel'i frame'e ekliyoruz
        frame.add(panel);

        frame.setVisible(true);  // Pencereyi görünür yapıyoruz
    }

}