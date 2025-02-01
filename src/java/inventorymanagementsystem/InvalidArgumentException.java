public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String name) {
        super("[ERROR] Invalid arguments for action '" + name + "'");
    }
}
