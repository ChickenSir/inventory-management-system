package action;
import java.util.HashMap;

public class ActionList {
    private static ActionList instance = null;

    private static HashMap<String, Action> actions = new HashMap<String, Action>() {{
        put("exit", new ExitAction());
        put("inventory", new InventoryAction());
        put("inv", new InventoryAction());
        put("list", new ListAction());
    }};

    private ActionList() {}

    public static ActionList getInstance() {
        if (instance == null) instance = new ActionList();

        return instance;
    }

    public Action getAction(String name) {
        return actions.get(name);
    }
}
