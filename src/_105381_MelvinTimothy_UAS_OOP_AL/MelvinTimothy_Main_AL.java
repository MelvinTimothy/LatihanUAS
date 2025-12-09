package _105381_MelvinTimothy_UAS_OOP_AL;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MelvinTimothy_Main_AL {
    private static Scanner scanner = new Scanner(System.in);
    private static final String ITEMS_FILE = "Items.txt";
    private static final String REPORT_FILE = "Report.txt";
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  FESTIVAL PERTUNJUKAN DRAMA - KASIR SYSTEM  ");
        System.out.println("         Developed by: Melvin Timothy         ");
        System.out.println("                NIM: 105381                   ");
        System.out.println("==============================================\n");
        
        loadAllData();
        
        boolean running = true;
        
        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        registerItem();
                        break;
                    case 2:
                        viewAllItems();
                        break;
                    case 3:
                        editItem();
                        break;
                    case 4:
                        deleteItem();
                        break;
                    case 5:
                        searchItem();
                        break;
                    case 6:
                        transaction();
                        break;
                    case 7:
                        salesReport();
                        break;
                    case 8:
                        checkFlashSale();
                        break;
                    case 9:
                        loadAllData();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\n✓ Terima kasih telah menggunakan sistem kasir!");
                        System.out.println("  Data telah tersimpan di " + ITEMS_FILE);
                        break;
                    default:
                        System.out.println("✗ Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           MAIN MENU                    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Pendaftaran Barang                  ║");
        System.out.println("║ 2. Lihat Semua Barang                  ║");
        System.out.println("║ 3. Edit Barang                         ║");
        System.out.println("║ 4. Hapus Barang                        ║");
        System.out.println("║ 5. Cari Barang                         ║");
        System.out.println("║ 6. Transaksi Pembelian                 ║");
        System.out.println("║ 7. Laporan Penjualan                   ║");
        System.out.println("║ 8. Check Flash Sale                    ║");
        System.out.println("║ 9. Load Data dari File                 ║");
        System.out.println("║ 0. Exit                                ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Pilih menu: ");
    }
    
    private static void registerItem() {
        try {
            System.out.println("\n===== PENDAFTARAN BARANG =====");
            System.out.println("Pilih kategori:");
            System.out.println("1. Ticket");
            System.out.println("2. Merchandise");
            System.out.println("3. Recording");
            System.out.print("Pilihan: ");
            
            int category = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nama barang: ");
            String name = scanner.nextLine();
            
            System.out.print("Harga: ");
            double price = scanner.nextDouble();
            
            if (price < 0) {
                throw new Exception("Harga tidak boleh negatif!");
            }
            
            System.out.print("Kuantitas: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            
            if (quantity < 0) {
                throw new Exception("Kuantitas tidak boleh negatif!");
            }
            
            System.out.print("Type/Location: ");
            String type = scanner.nextLine();
            
            switch (category) {
                case 1:
                    MelvinTimothy_Ticket_AL ticket = new MelvinTimothy_Ticket_AL(name, price, 0, type, "");
                    ticket.addItem(name, price, quantity, type);
                    ticket.saveToFile(ITEMS_FILE);
                    break;
                case 2:
                    MelvinTimothy_Merchandise_AL merch = new MelvinTimothy_Merchandise_AL(name, price, 0, type);
                    merch.addItem(name, price, quantity, type);
                    merch.saveToFile(ITEMS_FILE);
                    break;
                case 3:
                    MelvinTimothy_Recording_AL rec = new MelvinTimothy_Recording_AL(name, price, 0, type);
                    rec.addItem(name, price, quantity, type);
                    rec.saveToFile(ITEMS_FILE);
                    break;
                default:
                    System.out.println("✗ Kategori tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    private static void viewAllItems() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ALL ITEMS                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n----- TICKETS -----");
        if (MelvinTimothy_Ticket_AL.getTicketList().isEmpty()) {
            System.out.println("No tickets available.");
        } else {
            for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
                ticket.printDetails();
                System.out.println("-------------------------");
            }
        }
        
        System.out.println("\n----- MERCHANDISE -----");
        if (MelvinTimothy_Merchandise_AL.getMerchandiseList().isEmpty()) {
            System.out.println("No merchandise available.");
        } else {
            for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
                merch.printDetails();
                System.out.println("-------------------------");
            }
        }
        
        System.out.println("\n----- RECORDINGS -----");
        if (MelvinTimothy_Recording_AL.getRecordingList().isEmpty()) {
            System.out.println("No recordings available.");
        } else {
            for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
                rec.printDetails();
                System.out.println("-------------------------");
            }
        }
        
        saveAllItemsToFile();
    }
    
    private static void editItem() {
        try {
            System.out.println("\n===== EDIT BARANG =====");
            System.out.println("Pilih kategori:");
            System.out.println("1. Ticket");
            System.out.println("2. Merchandise");
            System.out.println("3. Recording");
            System.out.print("Pilihan: ");
            
            int category = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nama barang yang akan diedit: ");
            String name = scanner.nextLine();
            
            System.out.print("Harga baru (kosongkan jika tidak diubah): ");
            String priceStr = scanner.nextLine();
            Double price = priceStr.isEmpty() ? null : Double.parseDouble(priceStr);
            
            if (price != null && price < 0) {
                throw new Exception("Harga tidak boleh negatif!");
            }
            
            System.out.print("Kuantitas baru (kosongkan jika tidak diubah): ");
            String qtyStr = scanner.nextLine();
            Integer quantity = qtyStr.isEmpty() ? null : Integer.parseInt(qtyStr);
            
            if (quantity != null && quantity < 0) {
                throw new Exception("Kuantitas tidak boleh negatif!");
            }
            
            System.out.print("Type/Location baru (kosongkan jika tidak diubah): ");
            String type = scanner.nextLine();
            if (type.isEmpty()) type = null;
            
            switch (category) {
                case 1:
                    MelvinTimothy_Ticket_AL tempTicket = new MelvinTimothy_Ticket_AL("", 0, 0, "", "");
                    tempTicket.updateItem(name, price, quantity, type);
                    break;
                case 2:
                    MelvinTimothy_Merchandise_AL tempMerch = new MelvinTimothy_Merchandise_AL("", 0, 0, "");
                    tempMerch.updateItem(name, price, quantity, type);
                    break;
                case 3:
                    MelvinTimothy_Recording_AL tempRec = new MelvinTimothy_Recording_AL("", 0, 0, "");
                    tempRec.updateItem(name, price, quantity, type);
                    break;
                default:
                    System.out.println("✗ Kategori tidak valid!");
            }
            
            saveAllItemsToFile();
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    private static void deleteItem() {
        try {
            System.out.println("\n===== HAPUS BARANG =====");
            System.out.println("Pilih kategori:");
            System.out.println("1. Ticket");
            System.out.println("2. Merchandise");
            System.out.println("3. Recording");
            System.out.print("Pilihan: ");
            
            int category = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nama barang yang akan dihapus: ");
            String name = scanner.nextLine();
            
            boolean found = false;
            
            switch (category) {
                case 1:
                    for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
                        if (ticket.getName().equalsIgnoreCase(name)) {
                            ticket.deleteItem();
                            found = true;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
                        if (merch.getName().equalsIgnoreCase(name)) {
                            merch.deleteItem();
                            found = true;
                            break;
                        }
                    }
                    break;
                case 3:
                    for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
                        if (rec.getName().equalsIgnoreCase(name)) {
                            rec.deleteItem();
                            found = true;
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("✗ Kategori tidak valid!");
                    return;
            }
            
            if (!found) {
                System.out.println("✗ Barang tidak ditemukan!");
            }
            
            saveAllItemsToFile();
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    private static void searchItem() {
        try {
            System.out.println("\n===== CARI BARANG =====");
            System.out.print("Masukkan keyword pencarian: ");
            String keyword = scanner.nextLine();
            
            System.out.println("\nMencari di semua kategori...");
            
            MelvinTimothy_Ticket_AL tempTicket = new MelvinTimothy_Ticket_AL("", 0, 0, "", "");
            tempTicket.searchItem(keyword);
            
            MelvinTimothy_Merchandise_AL tempMerch = new MelvinTimothy_Merchandise_AL("", 0, 0, "");
            tempMerch.searchItem(keyword);
            
            MelvinTimothy_Recording_AL tempRec = new MelvinTimothy_Recording_AL("", 0, 0, "");
            tempRec.searchItem(keyword);
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void transaction() {
        try {
            System.out.println("\n===== TRANSAKSI PEMBELIAN =====");
            System.out.println("Pilih kategori:");
            System.out.println("1. Ticket");
            System.out.println("2. Merchandise");
            System.out.println("3. Recording");
            System.out.print("Pilihan: ");
            
            int category = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nama barang yang akan dibeli: ");
            String name = scanner.nextLine();
            
            System.out.print("Jumlah yang akan dibeli: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            
            if (qty <= 0) {
                throw new Exception("Jumlah pembelian harus lebih dari 0!");
            }
            
            System.out.print("Tanggal pembelian (DD-MM): ");
            String date = scanner.nextLine();
            
            MelvinTimothy_Items_AL selectedItem = null;
            
            switch (category) {
                case 1:
                    for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
                        if (ticket.getName().equalsIgnoreCase(name)) {
                            selectedItem = ticket;
                            break;
                        }
                    }
                    break;
                case 2:
                    for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
                        if (merch.getName().equalsIgnoreCase(name)) {
                            selectedItem = merch;
                            break;
                        }
                    }
                    break;
                case 3:
                    for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
                        if (rec.getName().equalsIgnoreCase(name)) {
                            selectedItem = rec;
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("✗ Kategori tidak valid!");
                    return;
            }
            
            if (selectedItem == null) {
                System.out.println("✗ Barang tidak ditemukan!");
                return;
            }
            
            if (qty > selectedItem.getQuantity()) {
                throw new Exception("Stok tidak mencukupi! Stok tersedia: " + selectedItem.getQuantity());
            }
            
            System.out.println("\n===== DETAIL TRANSAKSI =====");
            System.out.println("Barang: " + selectedItem.getName());
            System.out.println("Harga satuan: Rp" + String.format("%.2f", selectedItem.getPrice()));
            System.out.println("Jumlah: " + qty);
            
            double subtotal = selectedItem.getPrice() * qty;
            System.out.println("Subtotal: Rp" + String.format("%.2f", subtotal));
            
            // Check flash sale
            selectedItem.checkFlashSale(date);
            
            // Apply quantity discount
            double finalPrice = selectedItem.applyDiscount(qty);
            System.out.println("Total setelah diskon: Rp" + String.format("%.2f", finalPrice));
            
            // Process transaction
            selectedItem.transaction(qty, finalPrice);
            
            System.out.println("\n✓ Transaksi berhasil!");
            System.out.println("Sisa stok: " + selectedItem.getQuantity());
            
            // Save transaction to report
            saveTransactionReport(selectedItem.getName(), qty, finalPrice, date);
            saveAllItemsToFile();
            
        } catch (Exception e) {
            System.out.println("✗ Error transaksi: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    private static void salesReport() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                 LAPORAN PENJUALAN                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        double totalRevenue = 0;
        int totalItemsSold = 0;
        
        System.out.println("\n----- TICKET SALES -----");
        for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
            double revenue = ticket.getTotalRevenue();
            if (revenue > 0) {
                System.out.println("Item: " + ticket.getName());
                System.out.println("Total Revenue: Rp" + String.format("%.2f", revenue));
                System.out.println("Transactions: " + ticket.getTransactionRecords().size());
                System.out.println("-------------------------");
                totalRevenue += revenue;
                totalItemsSold += ticket.getTransactionRecords().size();
            }
        }
        
        System.out.println("\n----- MERCHANDISE SALES -----");
        for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
            double revenue = merch.getTotalRevenue();
            if (revenue > 0) {
                System.out.println("Item: " + merch.getName());
                System.out.println("Total Revenue: Rp" + String.format("%.2f", revenue));
                System.out.println("Transactions: " + merch.getTransactionRecords().size());
                System.out.println("-------------------------");
                totalRevenue += revenue;
                totalItemsSold += merch.getTransactionRecords().size();
            }
        }
        
        System.out.println("\n----- RECORDING SALES -----");
        for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
            double revenue = rec.getTotalRevenue();
            if (revenue > 0) {
                System.out.println("Item: " + rec.getName());
                System.out.println("Total Revenue: Rp" + String.format("%.2f", revenue));
                System.out.println("Transactions: " + rec.getTransactionRecords().size());
                System.out.println("-------------------------");
                totalRevenue += revenue;
                totalItemsSold += rec.getTransactionRecords().size();
            }
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TOTAL SUMMARY                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("Total Items Sold: " + totalItemsSold);
        System.out.println("Total Revenue: Rp" + String.format("%.2f", totalRevenue));
        
        // Save report to file
        saveReportToFile(totalItemsSold, totalRevenue);
    }
    
    private static void checkFlashSale() {
        try {
            System.out.println("\n===== CHECK FLASH SALE =====");
            System.out.print("Masukkan tanggal (DD-MM): ");
            String date = scanner.nextLine();
            
            System.out.println("\nMengecek flash sale untuk tanggal: " + date);
            
            System.out.println("\n----- TICKET FLASH SALE -----");
            for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
                System.out.println("\nItem: " + ticket.getName());
                ticket.checkFlashSale(date);
            }
            
            System.out.println("\n----- MERCHANDISE FLASH SALE -----");
            for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
                System.out.println("\nItem: " + merch.getName());
                merch.checkFlashSale(date);
            }
            
            System.out.println("\n----- RECORDING FLASH SALE -----");
            for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
                System.out.println("\nItem: " + rec.getName());
                rec.checkFlashSale(date);
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void loadAllData() {
        try {
            // Clear existing data
            MelvinTimothy_Ticket_AL.clearList();
            MelvinTimothy_Merchandise_AL.clearList();
            MelvinTimothy_Recording_AL.clearList();
            
            // Load from file
            MelvinTimothy_Ticket_AL tempTicket = new MelvinTimothy_Ticket_AL("", 0, 0, "", "");
            tempTicket.loadFromFile(ITEMS_FILE);
            
            MelvinTimothy_Merchandise_AL tempMerch = new MelvinTimothy_Merchandise_AL("", 0, 0, "");
            tempMerch.loadFromFile(ITEMS_FILE);
            
            MelvinTimothy_Recording_AL tempRec = new MelvinTimothy_Recording_AL("", 0, 0, "");
            tempRec.loadFromFile(ITEMS_FILE);
            
            System.out.println("✓ Data berhasil dimuat dari " + ITEMS_FILE);
        } catch (Exception e) {
            System.out.println("✗ Error loading data: " + e.getMessage());
        }
    }
    
    private static void saveAllItemsToFile() {
        try {
            // Clear file first
            PrintWriter writer = new PrintWriter(ITEMS_FILE);
            writer.close();
            
            // Save all items
            for (MelvinTimothy_Ticket_AL ticket : MelvinTimothy_Ticket_AL.getTicketList()) {
                ticket.saveToFile(ITEMS_FILE);
            }
            
            for (MelvinTimothy_Merchandise_AL merch : MelvinTimothy_Merchandise_AL.getMerchandiseList()) {
                merch.saveToFile(ITEMS_FILE);
            }
            
            for (MelvinTimothy_Recording_AL rec : MelvinTimothy_Recording_AL.getRecordingList()) {
                rec.saveToFile(ITEMS_FILE);
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error saving data: " + e.getMessage());
        }
    }
    
    private static void saveTransactionReport(String itemName, int qty, double total, String date) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE, true))) {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            writer.write(now + " | " + itemName + " | Qty: " + qty + " | Total: Rp" + 
                        String.format("%.2f", total) + " | Date: " + date);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("✗ Error saving transaction report: " + e.getMessage());
        }
    }
    
    private static void saveReportToFile(int totalItems, double totalRevenue) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE, true))) {
            writer.write("\n===== SALES SUMMARY =====");
            writer.newLine();
            writer.write("Total Items Sold: " + totalItems);
            writer.newLine();
            writer.write("Total Revenue: Rp" + String.format("%.2f", totalRevenue));
            writer.newLine();
            writer.write("Generated at: " + LocalDate.now());
            writer.newLine();
            writer.write("=========================\n");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("✗ Error saving report: " + e.getMessage());
        }
    }
}