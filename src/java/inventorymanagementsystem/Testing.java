public class Testing {
    public static void main(String[] args) {
        Inventory inv = new Inventory(3, 3);

        inv.add("one", 0 ,0);
        inv.add("one", 0 ,1);
        inv.add("one", 0 ,2);
        inv.add("one", 1 ,0);
        inv.add("one", 1 ,1);
        inv.add("one",1 ,2);
        inv.add("one", 2 ,0);
        inv.add("one", 2 ,1);
        inv.add("two", 2 ,2);

        inv.remove("two");

        System.out.println(inv.isFull());

        System.out.println(inv.toString());
    }
}
