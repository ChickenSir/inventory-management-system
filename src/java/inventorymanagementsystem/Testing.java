public class Testing {
    public static void main(String[] args) {
        Inventory inv = new Inventory(3, 3);

        for (int i = 0; i < inv.size; i++) {
            inv.add("test" + i);
        }

        System.out.println(inv.isFull());

        System.out.println(inv.toString());
    }
}
