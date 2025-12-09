package _105381_MelvinTimothy_UAS_OOP_AL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MelvinTimothy_Ticket_AL extends MelvinTimothy_Items_AL {
    private String seatNumber;
    private String location;
    private static List<MelvinTimothy_Ticket_AL> ticketList = new ArrayList<>();
    
    public MelvinTimothy_Ticket_AL(String name, double price, int quantity, String location, String seatNumber) {
        super(name, price, quantity, "Ticket", "Performance");
        this.location = location;
        this.seatNumber = seatNumber;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public void addItem(String name, double price, int quantity, String type) {
        MelvinTimothy_Ticket_AL ticket = new MelvinTimothy_Ticket_AL(name, price, quantity, type, "SEAT-" + (ticketList.size() + 1));
        ticketList.add(ticket);
        System.out.println("✓ Ticket added successfully!");
    }
    
    @Override
    public void updateItem(String name, Double price, Integer quantity, String type) {
        for (MelvinTimothy_Ticket_AL t : ticketList) {
            if (t.getName().equalsIgnoreCase(name)) {
                if (price != null) t.setPrice(price);
                if (quantity != null) t.setQuantity(quantity);
                if (type != null) t.setLocation(type);
                System.out.println("✓ Ticket updated successfully!");
                return;
            }
        }
        System.out.println("✗ Ticket not found!");
    }
    
    @Override
    public void deleteItem() {
        ticketList.remove(this);
        System.out.println("✓ Ticket deleted successfully!");
    }
    
    @Override
    public void searchItem(String keyword) {
        boolean found = false;
        System.out.println("\n===== SEARCH RESULTS - TICKET =====");
        for (MelvinTimothy_Ticket_AL t : ticketList) {
            if (t.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                t.getLocation().toLowerCase().contains(keyword.toLowerCase())) {
                t.printDetails();
                System.out.println("-------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("✗ No ticket found with keyword: " + keyword);
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
            writer.write("Ticket," + getName() + "," + getPrice() + "," + 
                        getQuantity() + "," + location + "," + seatNumber);
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
                if (parts[0].equals("Ticket") && parts.length >= 6) {
                    ticketList.add(new MelvinTimothy_Ticket_AL(parts[1], 
                        Double.parseDouble(parts[2]), 
                        Integer.parseInt(parts[3]), 
                        parts[4],
                        parts[5]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty data.");
        } catch (IOException e) {
            System.out.println("✗ Error loading from file: " + e.getMessage());
        }
    }
    
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Location: " + location);
        System.out.println("Seat Number: " + seatNumber);
    }
    
    public static List<MelvinTimothy_Ticket_AL> getTicketList() {
        return ticketList;
    }
    
    public static void clearList() {
        ticketList.clear();
    }
}