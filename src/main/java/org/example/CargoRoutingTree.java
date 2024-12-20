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

    // Constructor
    public CargoRoutingTree() {
        this.cargoCenter = new TreeNode("Cargo Center", 0); // Root node
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

    public void showCitiesAndDistricts() {
        String[] cities = {"İstanbul", "Ankara", "Trabzon", "İzmir", "Hatay"};
        String[] district = {
                "Üsküdar", "Beyoğlu", "Bakırköy",
                "Çankaya", "Keçiören", "Mamak",
                "Ortahisar", "Sürmene", "Akçaabat",
                "Konak", "Alsancak", "Karşıyaka",
                "Antakya", "İskenderun", "Defne"
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
