package inventory;
public class InventoryActions {
    private static InventoryList inventoryList = InventoryList.getInstance();

    public static void create(String name, int r, int c) throws InventoryAccessException {
        // Create an inventory of specified size and add it to the inventory list
        Inventory inv = new Inventory(r, c);
        inventoryList.addInventory(name, inv);
    }

    public static String list() {
        // List all created inventories
        String output = "=====[Inventory List]=====\n";

        for (String s: inventoryList.getList().keySet()) {
            output += " - " + s + "\n";
        }

        return output;
    }

    public static String display(String name) throws InventoryAccessException {
        // Return contents of inventory
        return inventoryList.getInventory(name).toString();
    }

    public static void add(String name, String s) throws InventoryAccessException {
        // Add string to inventory if not full
        Boolean added = inventoryList.getInventory(name).add(s);
        if (!added) throw new InventoryAccessException(name, "is full");
    }

    public static void add(String name, String s, int r, int c) throws InventoryAccessException {
        // Add string to inventory at specified slot if not full
        Boolean added = inventoryList.getInventory(name).add(s, r, c);
        if (!added) throw new InventoryAccessException(name, "is full or slot is occupied");
    }

    public static void remove(String name, String s) throws InventoryAccessException, StringAccessException {
        // Remove string from inventory if it is present
        String removed = inventoryList.getInventory(name).remove(s);
        if (removed == null) throw new StringAccessException(name, s);
    }

    public static void transfer(String from, String to, String s) throws InventoryAccessException, StringAccessException {
        // Get both inventories from the inventory list
        Inventory invFrom = inventoryList.getInventory(from);
        Inventory invTo = inventoryList.getInventory(to);

        // Transfer string between inventories if the string is present
        if (!invTo.isFull()) {
            String removed = invFrom.remove(s);
            if (removed == null) throw new StringAccessException(from, s);

            invTo.add(removed);
        } else {
            // Recieving inventory is full
            throw new InventoryAccessException(to, "is full");
        }
    }

    public static void delete(String name) throws InventoryAccessException {
        // Remove an inventory from the inventory list
        inventoryList.removeInventory(name);
    }

    public static void clear(String name) throws InventoryAccessException {
        // Clear an inventory if it is not empty
        Inventory inv = inventoryList.getInventory(name);
        if (inv.isEmpty()) throw new InventoryAccessException(name, "is empty");

        inv.clear();
    }

    public static void fill(String name) throws InventoryAccessException {
        // Fill an inventory if it is not full
        Inventory inv = inventoryList.getInventory(name);
        if (inv.isFull()) throw new InventoryAccessException(name, "is full");

        for (int i = 0; i < inv.size; i++) {
            String random = "" + (int)(Math.random() * 1000);
            inv.add(random);
        }
    }
}
