package _105381_MelvinTimothy_UAS_OOP_AL;

public abstract class MelvinTimothy_Items_AL extends MelvinTimothy_Invoice_AL implements MelvinTimothy_Discountable_AL {
    private String category;
    private String type;
    
    public MelvinTimothy_Items_AL(String name, double price, int quantity, String category, String type) {
        super(name, price, quantity);
        this.category = category;
        this.type = type;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public abstract void addItem(String name, double price, int quantity, String type);
    public abstract void updateItem(String name, Double price, Integer quantity, String type);
    public abstract void deleteItem();
    public abstract void searchItem(String keyword);
    public abstract double applyDiscount(int quantity);
    public abstract void checkFlashSale(String date);
    public abstract void saveToFile(String filename);
    public abstract void loadFromFile(String filename);
    
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Category: " + category);
        System.out.println("Type: " + type);
    }
}