package inventory;

public class StringAccessException extends Exception {
    public StringAccessException(String name, String str) {
        super("[ERROR] String '" + str + "' not present in '" + name + "'");
    }
}
