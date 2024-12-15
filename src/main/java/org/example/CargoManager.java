package org.example;

import java.util.*;

public class CargoManager {
    private List<Cargo> deliveredCargoList;  // Teslim edilmiş kargoların listesi
    private List<Cargo> undeliveredCargoList; // Teslim edilmemiş kargoların listesi

    public CargoManager() {
        this.deliveredCargoList = new ArrayList<>();
        this.undeliveredCargoList = new ArrayList<>();
    }

    // Teslim edilmiş kargo ekleme
    public void addDeliveredCargo(Cargo cargo) {
        deliveredCargoList.add(cargo);
    }

    // Teslim edilmemiş kargo ekleme
    public void addUndeliveredCargo(Cargo cargo) {
        undeliveredCargoList.add(cargo);
    }

    // Teslim edilmiş kargoları Binary Search ile arama
    public Cargo binarySearch(String cargoID) {
        // Teslim edilmiş kargoları ID'ye göre sıralıyoruz
        Collections.sort(deliveredCargoList, Comparator.comparing(Cargo::getCargoID));

        int low = 0;
        int high = deliveredCargoList.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Cargo midCargo = deliveredCargoList.get(mid);
            if (midCargo.getCargoID().equals(cargoID)) {
                return midCargo;  // Kargo bulundu
            } else if (midCargo.getCargoID().compareTo(cargoID) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  // Kargo bulunamadı
    }

    // Teslim edilmemiş kargoları Merge Sort ile sıralama
    public void sortUndeliveredCargo() {
        mergeSort(undeliveredCargoList);
    }

    // Merge Sort ile kargoları sıralama (Teslim süresine göre)
    private void mergeSort(List<Cargo> cargoList) {
        if (cargoList.size() > 1) {
            int mid = cargoList.size() / 2;
            List<Cargo> left = cargoList.subList(0, mid);
            List<Cargo> right = cargoList.subList(mid, cargoList.size());

            mergeSort(left);
            mergeSort(right);
            merge(cargoList, left, right);
        }
    }

    // Merge işlemi (kargoları teslim süresine göre birleştiriyor)
    private void merge(List<Cargo> cargoList, List<Cargo> left, List<Cargo> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getDeliveryTime() <= right.get(j).getDeliveryTime()) {
                cargoList.set(k++, left.get(i++));
            } else {
                cargoList.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            cargoList.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            cargoList.set(k++, right.get(j++));
        }
    }

    // Sıralanmış teslim edilmemiş kargo listesini yazdırma
    public void printUndeliveredCargoList() {
        for (Cargo cargo : undeliveredCargoList) {
            System.out.println(cargo);
        }
    }

    // Teslim edilmiş kargo listesini yazdırma
    public void printDeliveredCargoList() {
        for (Cargo cargo : deliveredCargoList) {
            System.out.println(cargo);
        }
    }
}

