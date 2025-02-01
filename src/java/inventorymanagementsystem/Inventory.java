public class Inventory {
    private String[][] items;
    private int rows;
    private int columns;
    public int size;

    private int nextRow = 0;
    private int nextColumn = 0;

    public Inventory(int r, int c) {
        items = new String[r][c];
        rows = r;
        columns = c;
        size = r * c;
    }

    public boolean add(String s) {
        if (isFull()) return false;

        items[nextRow][nextColumn] = s;
        if (nextColumn == columns - 1) nextRow++;
        nextColumn = (nextColumn + 1) % columns;

        return true;
    }

    public boolean isFull() {
        return nextRow == rows;
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
