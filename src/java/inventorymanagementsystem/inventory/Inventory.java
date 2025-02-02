package inventory;
public class Inventory {
    private String[][] items;
    private int rows;
    private int columns;
    public int size;

    private int nextRow = 0;
    private int nextColumn = 0;
    private int totalItems = 0;

    public Inventory(int r, int c) {
        items = new String[r][c];
        rows = r;
        columns = c;
        size = r * c;
    }

    public boolean add(String s) {
        if (isFull()) return false;

        // Add string to current slot if it is available
        if (items[nextRow][nextColumn] == null) {
            items[nextRow][nextColumn] = s;
            if (nextColumn == columns - 1) nextRow++;
            nextColumn = (nextColumn + 1) % columns;
        } else {
            // Use linear probing to find an available slot
            while (items[nextRow][nextColumn] != null) {
                if (nextColumn == columns - 1) nextRow++;
                nextColumn = (nextColumn + 1) % columns;
            }

            items[nextRow][nextColumn] = s;
        }

        totalItems++;
        return true;
    }

    public boolean add(String s, int r, int c) {
        if (isFull()) return false;
        if (items[r][c] != null) return false;

        // Add string to specified spot
        items[r][c] = s;
        totalItems++;

        return true;
    }

    public String remove(String s) {
        if (totalItems == 0) return null;

        // Find and remove specified string
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                if (items[i][j].equals(s)) {
                    if (i < nextRow) nextRow = i;
                    if (j < nextColumn) nextColumn = j;
                    
                    String temp = items[i][j];
                    items[i][j] = null;
                    totalItems--;
                    return temp;
                }
            }
        }

        return null;
    }

    public void clear() {
        // Clear the contents of inventory and reset field values
        items = new String[rows][columns];
        nextRow = 0;
        nextColumn = 0;
        totalItems = 0;
    }

    public boolean isFull() {
        return totalItems == size;
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
