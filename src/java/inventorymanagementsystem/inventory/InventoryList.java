package inventory;
import java.util.HashMap;

public class InventoryList {
    private static InventoryList instance = null;

    private static HashMap<String, Inventory> inventories;

    private InventoryList() {
        inventories = new HashMap<String, Inventory>();
    }

    public static InventoryList getInstance() {
        if (instance == null) instance = new InventoryList();

        return instance;
    }

    public Inventory getInventory(String name) throws InventoryAccessException {
        // Get inventory from inventory list if present
        Inventory inv = inventories.get(name);

        if (inv == null) throw new InventoryAccessException(name, "does not exist");

        return inventories.get(name);
    }

    public boolean addInventory(String name, Inventory inv) throws InventoryAccessException {
        // Add inventory to inventory list if it is not already present
        Inventory inventory = inventories.get(name);

        if (inventory != null) throw new InventoryAccessException(name, "already exists");

        inventories.put(name, inv);
        return true;
    }

    public boolean removeInventory(String name) throws InventoryAccessException {
        // Remove inventory from inventory list if it is present
        Inventory inv = inventories.get(name);

        if (inv == null) throw new InventoryAccessException(name, "does not exist");

        inventories.remove(name);
        return true;
    }

    public HashMap<String, Inventory> getList() {
        return inventories;
    }
}
