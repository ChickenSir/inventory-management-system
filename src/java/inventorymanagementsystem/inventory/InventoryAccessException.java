package inventory;
public class InventoryAccessException extends Exception {
    public InventoryAccessException(String name) {
        super("[ERROR] Inventory '" + name + "' does not exist");
    }
}
