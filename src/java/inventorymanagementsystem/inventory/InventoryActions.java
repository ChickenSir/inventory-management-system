package inventory;
public class InventoryActions {
    private static InventoryList inventoryList = InventoryList.getInstance();

    public static boolean create(String name, int r, int c) throws DuplicateInventoryException {
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

    public static void add(String name, String s) throws InventoryAccessException {
        Boolean added = inventoryList.getInventory(name).add(s);
        if (!added) throw new InventoryAccessException(name, "is full");
    }

    public static void add(String name, String s, int r, int c) throws InventoryAccessException {
        Boolean added = inventoryList.getInventory(name).add(s, r, c);
        if (!added) throw new InventoryAccessException(name, "is full");
    }

    public static void remove(String name, String s) throws InventoryAccessException, StringAccessException {
        String removed = inventoryList.getInventory(name).remove(s);
        if (removed == null) throw new StringAccessException(name, s);
    }

    public static void transfer(String from, String to, String s) throws InventoryAccessException, StringAccessException {
        Inventory invFrom = inventoryList.getInventory(from);
        Inventory invTo = inventoryList.getInventory(to);

        if (!invTo.isFull()) {
            String removed = invFrom.remove(s);
            if (removed == null) throw new StringAccessException(from, s);

            invTo.add(removed);
        } else {
            throw new InventoryAccessException(to, "is full");
        }
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
