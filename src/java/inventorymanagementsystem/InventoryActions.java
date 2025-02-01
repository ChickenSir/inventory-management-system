public class InventoryActions {
    private static InventoryList inventoryList = InventoryList.getInstance();

    public static boolean create(String name, int r, int c) {
        Inventory inv = new Inventory(r, c);
        inventoryList.addInventory(name, inv);

        return true;
    }

    public static String list() {
        String output = "=====[Inventory List]=====\n";

        for (String s: inventoryList.getList().keySet()) {
            output += " - " + s + "\n";
        }

        return output;
    }
}
