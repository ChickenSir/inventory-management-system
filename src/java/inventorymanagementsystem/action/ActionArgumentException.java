package action;
public class ActionArgumentException extends Exception {
    public ActionArgumentException(String action, int num) {
        super("[ERROR] Action '" + action + "' takes " + num + " argument(s)");
    }
    
}
