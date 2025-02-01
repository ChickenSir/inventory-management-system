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

        if (items[nextRow][nextColumn] == null) {
            items[nextRow][nextColumn] = s;
            if (nextColumn == columns - 1) nextRow++;
            nextColumn = (nextColumn + 1) % columns;
        } else {
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

        items[r][c] = s;
        totalItems++;

        return true;
    }

    public String remove(String s) {
        if (totalItems == 0) return null;

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                if (items[i][j].equals(s)) {
                    items[i][j] = null;
                    return items[i][j];
                }
            }
        }

        return null;
    }

    public void clear() {
        items = new String[rows][columns];
        nextRow = 0;
        nextColumn = 0;
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
