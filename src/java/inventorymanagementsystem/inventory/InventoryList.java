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

    public Inventory getInventory(String name) throws InventoryRetrievalException {
        Inventory inv = inventories.get(name);

        if (inv == null) throw new InventoryRetrievalException(name);

        return inventories.get(name);
    }

    public boolean addInventory(String name, Inventory inv) {
        Inventory inventory = inventories.get(name);

        if (inventory != null) return false;

        inventories.put(name, inv);
        return true;
    }

    public boolean removeInventory(String name) throws InventoryRetrievalException {
        Inventory inv = inventories.get(name);

        if (inv == null) throw new InventoryRetrievalException(name);

        inventories.remove(name);
        return true;
    }

    public HashMap<String, Inventory> getList() {
        return inventories;
    }
}
