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
            case "create": {
                if (args.size() > 4 || args.size() < 3) {
                    throw new ActionArgumentException(action, 4);
                }

                // Create a new inventory
                int rows = Integer.valueOf(args.get(1));
                int columns = Integer.valueOf(args.get(2));
                String name;
                if (args.size() == 4) {
                    name = args.get(3);
                } else {
                    name = "Inventory" + (int)(Math.random() * 10000);
                }

                InventoryActions.create(name, rows, columns);
                output = "[SYSTEM] Succesfully created inventory '" + name + "'";
                break;
            }
            case "list":
                if (args.size() > 1) {
                    throw new ActionArgumentException(action, 0);
                }

                output = InventoryActions.list();
                break;
            case "display":
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                output += "[SYSTEM] Displaying " + args.get(1) + "\n";
                output += InventoryActions.display(args.get(1));
                break;
            case "add": {
                if (args.size() < 3 || args.size() > 5) {
                    throw new ActionArgumentException(action, 2);
                }

                String name = args.get(2);
                String str = args.get(1);
                Boolean added;

                if (args.size() == 3) {
                    added = InventoryActions.add(name, str);
                } else {
                    int row = Integer.valueOf(args.get(3));
                    int column = Integer.valueOf(args.get(4));

                    added = InventoryActions.add(name, str, row, column);
                }

                if (added) {
                    output = "[SYSTEM] Added '" + str + "' to '" + name + "' ";
                } else {
                    output = "[ERROR] Inventory '" + name + "' is full or slot is occupied";
                }

                break;
            }
        }

        return output;
    }
}
