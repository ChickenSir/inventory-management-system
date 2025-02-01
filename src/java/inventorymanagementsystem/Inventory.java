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

    public String toString() {
        String output = "";

        for(int i = 0; i < items.length; i++) {
            for(int j = 0; j < items[i].length; j++) {
                String item = (items[i][j] == null) ? "-" : "\"" + items[i][j] + "\"";
                output += "[" + item + "] ";
            }

            output += "\n";
        }

        return output;
    }
}
