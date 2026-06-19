import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InventorySystem {
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Map<String, Item> itemById = new HashMap<>();
        Set<String> categories = new HashSet<>();

        loadInventory(items, itemById, categories);

        while (true) {
            print("\nInventory System");
            print("1. Add Item");
            print("2. List Inventory");
            print("3. Search by Category");
            print("4. Restock Item");
            print("5. Low Stock Report");
            print("6. Save and Exit");
            print("Enter choice: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    addItem(items, itemById, categories);
                    break;
                case 2:
                    listInventory(items);
                    break;
                case 3:
                    searchByCategory(items);
                    break;
                case 4:
                    restockItem(itemById);
                    break;
                case 5:
                    lowStockReport(items);
                    break;
                case 6:
                    saveInventory(items);
                    print("Inventory saved. Exiting.");
                    return;
                default:
                    print("Please choose a valid option.");
            }
        }
    }

    private static void print(Object... values) {
        for (Object value : values) {
            System.out.println(value);
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            print("Invalid number. Try again.");
            return -1;
        }
    }

    private static void loadInventory(List<Item> items,
                                      Map<String, Item> itemById,
                                      Set<String> categories) {
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Item item = new Item(parts[0], parts[1], parts[2],
                            Double.parseDouble(parts[3]), Integer.parseInt(parts[4]));
                    items.add(item);
                    itemById.put(parts[0], item);
                    categories.add(parts[2]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            print("Error loading inventory: " + e.getMessage());
        }
    }

    private static void saveInventory(List<Item> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Item item : items) {
                writer.write(item.getId() + "," + item.getName() + "," + item.getCategory() + "," + item.getPrice() + "," + item.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            print("Error saving inventory: " + e.getMessage());
        }
    }

    private static void addItem(List<Item> items,
                                Map<String, Item> itemById,
                                Set<String> categories) {
        print("Enter item ID:");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            print("Item ID cannot be empty.");
            return;
        }
        if (itemById.containsKey(id)) {
            print("Item ID already exists.");
            return;
        }

        print("Enter name:");
        String name = scanner.nextLine().trim();
        print("Enter category:");
        String category = scanner.nextLine().trim();
        print("Enter price:");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                print("Price cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            print("Invalid price.");
            return;
        }

        print("Enter quantity:");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity < 0) {
                print("Quantity cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            print("Invalid quantity.");
            return;
        }

        Item item = new Item(id, name, category, price, quantity);
        items.add(item);
        itemById.put(id, item);
        categories.add(category);
        print("Item added.");
    }

    private static void listInventory(List<Item> items) {
        if (items.isEmpty()) {
            print("No inventory items.");
            return;
        }
        for (Item item : items) {
            print(item);
        }
        double totalValue = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        print("Total inventory value: " + totalValue);
    }

    private static void searchByCategory(List<Item> items) {
        print("Enter category to search:");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) {
            print("Category cannot be empty.");
            return;
        }

        List<Item> result = items.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            print("No items found in category: " + category);
            return;
        }
        for (Item item : result) {
            print(item);
        }
    }

    private static void restockItem(Map<String, Item> itemById) {
        print("Enter item ID to restock:");
        String id = scanner.nextLine().trim();
        if (!itemById.containsKey(id)) {
            print("Item not found.");
            return;
        }
        print("Enter quantity to add:");
        try {
            int addQty = Integer.parseInt(scanner.nextLine());
            if (addQty <= 0) {
                print("Quantity must be positive.");
                return;
            }
            Item item = itemById.get(id);
            item.setQuantity(item.getQuantity() + addQty);
            print("Item restocked.");
        } catch (NumberFormatException e) {
            print("Invalid quantity.");
        }
    }

    private static void lowStockReport(List<Item> items) {
        List<Item> lowStock = items.stream()
                .filter(item -> item.getQuantity() <= 5)
                .collect(Collectors.toList());
        if (lowStock.isEmpty()) {
            print("No low stock items.");
            return;
        }
        for (Item item : lowStock) {
            print(item);
        }
    }

    static class Item {
        private final String id;
        private final String name;
        private final String category;
        private final double price;
        private int quantity;

        public Item(String id, String name, String category, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            if (quantity >= 0) {
                this.quantity = quantity;
            }
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Category: " + category + ", Price: " + price + ", Qty: " + quantity;
        }
    }
}
