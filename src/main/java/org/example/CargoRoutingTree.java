package org.example;
import java.util.*;

// CargoRoutingTree class
public class CargoRoutingTree {

    // TreeNode class to represent each node in the tree
    private static class TreeNode {
        String name; // Name of the node (City or District)
        int depth;   // Depth level in the tree (0: Root, 1: City, 2: District)
        int deliveryTime; // Delivery time for district (in days)
        List<TreeNode> children; // List of child nodes

        public TreeNode(String name, int depth, int deliveryTime) {
            this.name = name;
            this.depth = depth;
            this.deliveryTime = deliveryTime;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode child) {
            children.add(child);
        }
    }

    // Root node (Cargo Center)
    private final TreeNode cargoCenter;
    // Harita: Şehir -> İlçeler
    private Map<String, List<String>> cityDistrictMap;

    // Constructor
    public CargoRoutingTree() {
        this.cargoCenter = new TreeNode("Cargo Center", 0, 0); // Root node
        // Şehir ve ilçeleri burada tanımlıyoruz
        cityDistrictMap = new HashMap<>();

        // Örnek şehirler ve ilçeler
        cityDistrictMap.put("İstanbul", Arrays.asList("Üsküdar", "Kadıköy", "Beyoğlu"));
        cityDistrictMap.put("Ankara", Arrays.asList("Çankaya", "Keçiören", "Mamak"));
        cityDistrictMap.put("İzmir", Arrays.asList("Konak", "Karşıyaka", "Bornova"));
        cityDistrictMap.put("Bursa", Arrays.asList("Osmangazi", "Nilüfer", "Yıldırım"));
        cityDistrictMap.put("Antalya", Arrays.asList("Muratpaşa", "Kepez", "Konyaaltı"));
        // Daha fazla şehir ve ilçe eklenebilir

        // Şehirleri ve ilçeleri ağaca ekliyoruz
        initializeTree();
    }

    // Initialize the tree with cities and districts
    private void initializeTree() {
        for (Map.Entry<String, List<String>> entry : cityDistrictMap.entrySet()) {
            String cityName = entry.getKey();
            List<String> districts = entry.getValue();

            // Şehri ağaca ekle
            TreeNode city = new TreeNode(cityName, 1, 0);  // Şehre teslimat süresi 0
            cargoCenter.addChild(city);

            // Şehir altındaki ilçeleri ağaca ekle
            for (int i = 0; i < districts.size(); i++) {
                String districtName = districts.get(i);
                // Teslimat süresi: İlk ilçeye 2, ikinciye 4, üçüncüye 6 vb.
                int deliveryTime = 2 + (i * 2);  // Teslimat süresi 2, 4, 6 şeklinde
                TreeNode district = new TreeNode(districtName, 2, deliveryTime);
                city.addChild(district);
            }
        }
    }

    // City exists in the tree
    public boolean cityExists(String cityName) {
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    // District exists for the given city
    public boolean districtExists(String cityName, String districtName) {
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                for (TreeNode district : city.children) {
                    if (district.name.equals(districtName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Calculate delivery time based on city and district
    public int calculateDeliveryTime(String cityName, String districtName) {
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                if (districtName == null) {
                    return city.deliveryTime; // Only city delivery time
                }
                for (TreeNode district : city.children) {
                    if (district.name.equals(districtName)) {
                        return city.deliveryTime + district.deliveryTime; // City + District delivery time
                    }
                }
                throw new IllegalArgumentException("District not found: " + districtName);
            }
        }
        throw new IllegalArgumentException("City not found: " + cityName);
    }


    // Display the tree
    public void displayTree(StringBuilder sb) {
        sb.append(cargoCenter.name).append("\n"); // Root node
        for (TreeNode city : cargoCenter.children) {
            sb.append("  -> ").append(city.name).append("\n"); // Cities
            for (TreeNode district : city.children) {
                sb.append("    -> ").append(district.name)
                        .append(" (Teslimat Süresi: ").append(district.deliveryTime).append(" gün)\n"); // Districts
            }
        }
    }

    // List all cities and districts in a readable format
    public String getCitiesAndDistricts() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : cityDistrictMap.entrySet()) {
            String city = entry.getKey();
            List<String> districts = entry.getValue();
            sb.append("Şehir: ").append(city).append("\n");
            for (String district : districts) {
                sb.append("  İlçe: ").append(district).append("\n");
            }
        }
        sb.append("\nLütfen şehir ve ilçe ismini doğru yazdığınızdan emin olunuz. Büyük harfle başlamasına dikkat ediniz.");
        return sb.toString();  // Return the formatted string
    }
}