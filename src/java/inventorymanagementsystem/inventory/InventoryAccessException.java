package inventory;
public class InventoryAccessException extends Exception {
    public InventoryAccessException(String name, String details) {
        super("[ERROR] Inventory '" + name + "' " + details);
    }
}
