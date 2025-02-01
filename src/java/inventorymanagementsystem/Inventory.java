public class Inventory {
    private String[][] items;
    private int nextRow;
    private int nextColumn;
    private int size;

    public Inventory(int rows, int columns) {
        items = new String[rows][columns];
        nextRow = 0;
        nextColumn = 0;
        size = rows * columns;
    }
}
