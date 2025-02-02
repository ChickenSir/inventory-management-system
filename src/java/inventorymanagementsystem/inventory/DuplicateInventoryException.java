package inventory;

public class DuplicateInventoryException extends Exception {
    public DuplicateInventoryException(String name) {
        super("[ERROR] Inventory '" + name + "' already exists");
    }
}
