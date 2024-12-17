package org.example;

public class CargoRoutingTree {
    public void showCitiesAndDistricts(){
        String[] cities = {"İstanbul", "Ankara", "Trabzon", "İzmir","Hatay"};
        String[] district= {
                "Üsküdar (341)", "Beyoğlu (342)", "Bakırköy (343)",
                "Çankaya (061)", "Keçiören (062)", "Mamak (063)",
                "Ortahisar (611)", "Sürmene (612)", "Akçaabat (613)",
                "Konak (351)", "Alsancak (352)", "Karşıyaka (353)",
                "Antakya (311)", "İskenderun (312)", "Defne (313)"
        };
        int i, j;
        int districtIndex = 0;

        for (i = 0; i < cities.length; i++) {
            System.out.println(cities[i] + ": ");
            for (j = 0; j < 3; j++) {  // Her şehire 3 ilçe yazdırıyoruz
                System.out.println("-> " + district[districtIndex]);
                districtIndex++;  // Bir sonraki ilçeye geçiyoruz
            }
        }
    }
}
