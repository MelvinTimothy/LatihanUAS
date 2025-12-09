package _105381_MelvinTimothy_UAS_OOP_AL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MelvinTimothy_Merchandise_AL extends MelvinTimothy_Items_AL {
    private static List<MelvinTimothy_Merchandise_AL> merchandiseList = new ArrayList<>();
    
    public MelvinTimothy_Merchandise_AL(String name, double price, int quantity, String type) {
        super(name, price, quantity, "Merchandise", type);
    }
    
    @Override
    public void addItem(String name, double price, int quantity, String type) {
        MelvinTimothy_Merchandise_AL merch = new MelvinTimothy_Merchandise_AL(name, price, quantity, type);
        merchandiseList.add(merch);
        System.out.println("✓ Merchandise added successfully!");
    }
    
    @Override
    public void updateItem(String name, Double price, Integer quantity, String type) {
        for (MelvinTimothy_Merchandise_AL m : merchandiseList) {
            if (m.getName().equalsIgnoreCase(name)) {
                if (price != null) m.setPrice(price);
                if (quantity != null) m.setQuantity(quantity);
                if (type != null) m.setType(type);
                System.out.println("✓ Merchandise updated successfully!");
                return;
            }
        }
        System.out.println("✗ Merchandise not found!");
    }
    
    @Override
    public void deleteItem() {
        merchandiseList.remove(this);
        System.out.println("✓ Merchandise deleted successfully!");
    }
    
    @Override
    public void searchItem(String keyword) {
        boolean found = false;
        System.out.println("\n===== SEARCH RESULTS - MERCHANDISE =====");
        for (MelvinTimothy_Merchandise_AL m : merchandiseList) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                m.getType().toLowerCase().contains(keyword.toLowerCase())) {
                m.printDetails();
                System.out.println("-------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("✗ No merchandise found with keyword: " + keyword);
        }
    }
    
    @Override
    public double calculateTotal() {
        return getPrice() * getQuantity();
    }
    
    @Override
    public double applyDiscount(int quantity) {
        double total = getPrice() * quantity;
        if (quantity > 3) {
            total *= 0.9;
            System.out.println("✓ 10% discount applied for buying more than 3 items!");
        }
        return total;
    }
    
    @Override
    public double countDiscountedQuantity(int quantity) {
        return applyDiscount(quantity);
    }
    
    @Override
    public void checkFlashSale(String date) {
        String[] parts = date.split("-");
        if (parts.length == 2) {
            try {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                
                if (day == month) {
                    int discount = (int)(Math.random() * 31) + 10;
                    System.out.println("🎉 FLASH SALE! " + discount + "% discount available today!");
                    double discountedPrice = getPrice() * (100 - discount) / 100.0;
                    System.out.println("Original Price: Rp" + String.format("%.2f", getPrice()));
                    System.out.println("Flash Sale Price: Rp" + String.format("%.2f", discountedPrice));
                } else {
                    System.out.println("No flash sale today.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid date format!");
            }
        }
    }
    
    @Override
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Merchandise," + getName() + "," + getPrice() + "," + 
                        getQuantity() + "," + getType());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("✗ Error saving to file: " + e.getMessage());
        }
    }
    
    @Override
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Merchandise") && parts.length >= 5) {
                    merchandiseList.add(new MelvinTimothy_Merchandise_AL(parts[1], 
                        Double.parseDouble(parts[2]), 
                        Integer.parseInt(parts[3]), 
                        parts[4]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty data.");
        } catch (IOException e) {
            System.out.println("✗ Error loading from file: " + e.getMessage());
        }
    }
    
    public static List<MelvinTimothy_Merchandise_AL> getMerchandiseList() {
        return merchandiseList;
    }
    
    public static void clearList() {
        merchandiseList.clear();
    }
}