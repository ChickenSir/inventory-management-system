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

    public static String display(String name) {
        return inventoryList.getInventory(name).toString();
    }

    public static boolean add(String name, String s) {
        return inventoryList.getInventory(name).add(s);
    }

    public static boolean add(String name, String s, int r, int c) {
        return inventoryList.getInventory(name).add(s, r, c);
    }

    public static String remove(String name, String s) {
        return inventoryList.getInventory(name).remove(s);
    }

    public static boolean transfer(String from, String to, String s) {
        Inventory invFrom = inventoryList.getInventory(from);
        Inventory invTo = inventoryList.getInventory(to);

        return invTo.add(invFrom.remove(s));
    }

    public static void clear(String name) {
        inventoryList.getInventory(name).clear();
    }
}
