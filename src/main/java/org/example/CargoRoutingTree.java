package org.example;
import java.util.*;

// CargoRoutingTree class
public class CargoRoutingTree {

    // TreeNode class to represent each node in the tree
    private static class TreeNode {
        String name; // Name of the node (City or District)
        int depth;   // Depth level in the tree (0: Root, 1: City, 2: District)
        List<TreeNode> children; // List of child nodes

        public TreeNode(String name, int depth) {
            this.name = name;
            this.depth = depth;
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
        this.cargoCenter = new TreeNode("Cargo Center", 0); // Root node
        // Şehir ve ilçeleri burada tanımlıyoruz
        cityDistrictMap = new HashMap<>();

        // Örnek şehirler ve ilçeler
        cityDistrictMap.put("İstanbul", Arrays.asList("Üsküdar", "Kadıköy", "Beyoğlu"));
        cityDistrictMap.put("Ankara", Arrays.asList("Çankaya", "Keçiören", "Mamak"));
        cityDistrictMap.put("İzmir", Arrays.asList("Konak", "Karşıyaka", "Bornova"));
        cityDistrictMap.put("Bursa", Arrays.asList("Osmangazi", "Nilüfer", "Yıldırım"));
        cityDistrictMap.put("Antalya", Arrays.asList("Muratpaşa", "Kepez", "Konyaaltı"));
        // Daha fazla şehir ve ilçe eklenebilir
    }

    // Add a city to the tree
    public void addCity(String cityName) {
        // Check if the city already exists
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                return; // City already exists, no need to add
            }
        }
        // Add new city
        TreeNode city = new TreeNode(cityName, 1); // Cities are at depth 1
        cargoCenter.addChild(city);
    }

    // Add a district to a specific city
    public void addDistrict(String cityName, String districtName) {
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                // Check if the district already exists
                for (TreeNode district : city.children) {
                    if (district.name.equals(districtName)) {
                        return; // District already exists
                    }
                }
                // Add new district
                TreeNode district = new TreeNode(districtName, 2); // Districts are at depth 2
                city.addChild(district);
                return;
            }
        }
        throw new IllegalArgumentException("City not found: " + cityName);
    }

    // Şehir var mı kontrol et
    public boolean cityExists(String cityName) {
        return cityDistrictMap.containsKey(cityName);
    }

    // İlçe var mı ve şehirle uyumlu mu kontrol et
    public boolean districtExists(String cityName, String districtName) {
        if (cityExists(cityName)) {
            List<String> districts = cityDistrictMap.get(cityName);
            return districts.contains(districtName);
        }
        return false;
    }

    // Calculate delivery time based on depth
    public int calculateDeliveryTime(String cityName, String districtName) {
        for (TreeNode city : cargoCenter.children) {
            if (city.name.equals(cityName)) {
                if (districtName == null) {
                    return city.depth * 2; // Only city delivery time
                }
                for (TreeNode district : city.children) {
                    if (district.name.equals(districtName)) {
                        return city.depth * 2 + district.depth * 1; // City + District delivery time
                    }
                }
                throw new IllegalArgumentException("District not found: " + districtName);
            }
        }
        throw new IllegalArgumentException("City not found: " + cityName);
    }

    // Display the tree structure
    public void displayTree() {
        System.out.println(cargoCenter.name); // Print root
        for (TreeNode city : cargoCenter.children) {
            System.out.println("  -> " + city.name); // Print city
            for (TreeNode district : city.children) {
                System.out.println("    -> " + district.name); // Print district
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
        sb.append("\nLütfen şehir ve ilçe ismini doğru yazdığınızdan emin olunuz.");
        return sb.toString();  // Return the formatted string
    }


}

