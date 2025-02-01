public class InventoryRetrievalException extends Exception {
    public InventoryRetrievalException(String name) {
        super("[ERROR] Inventory '" + name + "' does not exist");
    }
    
}
