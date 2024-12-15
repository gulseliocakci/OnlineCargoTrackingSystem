package org.example;

import java.util.List;

public class CargoSearch {
    public static Cargo binarySearch(List<Cargo> cargoList, String cargoID) {
        int low = 0;
        int high = cargoList.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Cargo midCargo = cargoList.get(mid);
            if (midCargo.getCargoID().equals(cargoID)) {
                return midCargo;
            } else if (midCargo.getCargoID().compareTo(cargoID) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  // Kargo bulunamadı
    }
}
