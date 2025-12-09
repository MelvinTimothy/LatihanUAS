package _105381_MelvinTimothy_UAS_OOP_AL;

import java.util.ArrayList;
import java.util.List;

public abstract class MelvinTimothy_Invoice_AL implements MelvinTimothy_Printable_AL {
    private String name;
    private double price;
    private int quantity;
    private List<Double> transactionRecords;
    
    public MelvinTimothy_Invoice_AL(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.transactionRecords = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public List<Double> getTransactionRecords() {
        return transactionRecords;
    }
    
    public abstract double calculateTotal();
    
    public void transaction(int qty, double finalPrice) throws Exception {
        if (qty <= 0) {
            throw new Exception("Quantity must be greater than 0");
        }
        if (qty > this.quantity) {
            throw new Exception("Insufficient stock! Available: " + this.quantity);
        }
        this.quantity -= qty;
        transactionRecords.add(finalPrice);
    }
    
    public void addStock(int qty) throws Exception {
        if (qty <= 0) {
            throw new Exception("Quantity to add must be greater than 0");
        }
        this.quantity += qty;
    }
    
    public double getTotalRevenue() {
        double total = 0;
        for (double record : transactionRecords) {
            total += record;
        }
        return total;
    }
    
    @Override
    public void printDetails() {
        System.out.println("Item: " + name);
        System.out.println("Price: Rp" + String.format("%.2f", price));
        System.out.println("Stock: " + quantity);
    }
}