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

    public Inventory getInventory(String name) {
        return inventories.get(name);
    }

    public void addInventory(String name, Inventory inv) {
        inventories.put(name, inv);
    }

    public boolean removeInventory(String name) {
        Inventory inv = inventories.get(name);

        if (inv == null) return false;

        inventories.remove(name);
        return true;
    }

    public HashMap<String, Inventory> getList() {
        return inventories;
    }
}
