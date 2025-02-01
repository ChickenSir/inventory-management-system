package inventory;
public class InventoryActions {
    private static InventoryList inventoryList = InventoryList.getInstance();

    public static boolean create(String name, int r, int c) {
        Inventory inv = new Inventory(r, c);
        return inventoryList.addInventory(name, inv);
    }

    public static String list() {
        String output = "=====[Inventory List]=====\n";

        for (String s: inventoryList.getList().keySet()) {
            output += " - " + s + "\n";
        }

        return output;
    }

    public static String display(String name) throws InventoryAccessException {
        return inventoryList.getInventory(name).toString();
    }

    public static boolean add(String name, String s) throws InventoryAccessException {
        return inventoryList.getInventory(name).add(s);
    }

    public static boolean add(String name, String s, int r, int c) throws InventoryAccessException {
        return inventoryList.getInventory(name).add(s, r, c);
    }

    public static String remove(String name, String s) throws InventoryAccessException {
        return inventoryList.getInventory(name).remove(s);
    }

    public static boolean transfer(String from, String to, String s) throws InventoryAccessException {
        Inventory invFrom = inventoryList.getInventory(from);
        Inventory invTo = inventoryList.getInventory(to);

        return invTo.add(invFrom.remove(s));
    }

    public static boolean delete(String name) throws InventoryAccessException {
        return inventoryList.removeInventory(name);
    }

    public static boolean clear(String name) throws InventoryAccessException {
        Inventory inv = inventoryList.getInventory(name);
        if (inv == null) return false;

        inventoryList.getInventory(name).clear();
        return true;
    }

    public static boolean fill(String name) throws InventoryAccessException {
        Inventory inv = inventoryList.getInventory(name);
        if (inv == null) return false;

        for (int i = 0; i < inv.size; i++) {
            String random = "" + (int)(Math.random() * 1000);
            inv.add(random);
        }

        return true;
    }
}
