import java.util.List;

public interface Action {
    public String run(List<String> args) throws ActionArgumentException;
}

class ExitAction implements Action {
    @Override
    public String run(List<String> args) throws ActionArgumentException {
        if (args.size() != 0) {
            throw new ActionArgumentException("exit", 0);
        }
        System.exit(0);

        return null;
    }
} 

class InventoryAction implements Action {
    @Override
    public String run(List<String> args) throws ActionArgumentException {
        if (args.size() < 1) {
            throw new ActionArgumentException("inventory", 1);
        }

        // Get the first argument from the action
        String action = args.get(0);

        String output = "";

        switch (action) {
            case "create":
                // Create a new inventory
                int rows = Integer.valueOf(args.get(1));
                int columns = Integer.valueOf(args.get(2));
                String name = args.get(3);

                InventoryActions.create(name, rows, columns);
                output = "[SYSTEM] Succesfully created inventory";
                break;
            case "list":
                output = InventoryActions.list();
                break;
        }

        return output;
    }
}
