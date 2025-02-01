import java.util.List;

public interface Action {
    public String run(List<String> args) throws ActionArgumentException, InvalidArgumentException;
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

class ListAction implements Action {
    @Override
    public String run(List<String> args) throws ActionArgumentException {
        if (args.size() != 0) {
            throw new ActionArgumentException("exit", 0);
        }

        String output = "=====[List of Actions]=====\n\n"
            + " - list: Lists all actions\n"
            + " - inventory\n"
            + "    - create: Create an inventory with a specified size and name\n"
            + "    - list: List all inventories\n"
            + "    - display: Display the contents of an inventory\n"
            + "    - add: Add a string to an inventory\n"
            + "    - remove: Remove a string from an inventory\n"
            + "    - transfer: Transfer a string between two inventories\n"
            + "    - clear: Clear an inventory\n"
            + "    - fill: Fill an inventory\n"
            + " - help: Display details of an action\n"
            + " - exit: Exit the program";
        return output;
    }
}

class InventoryAction implements Action {
    @Override
    public String run(List<String> args) throws ActionArgumentException, InvalidArgumentException {
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
                int rows;
                int columns;

                try {
                    rows = Integer.valueOf(args.get(1));
                    columns = Integer.valueOf(args.get(2));
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException("create");
                }

                String name;
                if (args.size() == 4) {
                    name = args.get(3);
                } else {
                    name = "Inventory" + (int)(Math.random() * 10000);
                }

                Boolean inventoryCreated = InventoryActions.create(name, rows, columns);

                if (inventoryCreated) {
                    output = "[SYSTEM] Succesfully created inventory '" + name + "'";
                } else {
                    output = "[ERROR] Inventory '" + name + "' already exists";
                }
                
                break;
            }
            case "list": {
                if (args.size() > 1) {
                    throw new ActionArgumentException(action, 0);
                }

                output = InventoryActions.list();
                break;
            }
            case "display": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                output += "[SYSTEM] Displaying " + args.get(1) + "\n";
                output += InventoryActions.display(args.get(1));
                break;
            }
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
                    int row;
                    int column;

                    try {
                        row = Integer.valueOf(args.get(3));
                        column = Integer.valueOf(args.get(4));
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException("add");
                    }

                    added = InventoryActions.add(name, str, row, column);
                }

                if (added) {
                    output = "[SYSTEM] Added '" + str + "' to '" + name + "' ";
                } else {
                    output = "[ERROR] Inventory '" + name + "' is full or slot is occupied";
                }

                break;
            }
            case "remove": {
                if (args.size() != 3) {
                    throw new ActionArgumentException(action, 2);
                }

                String str = args.get(1);
                String name = args.get(2);
                String removed = InventoryActions.remove(name, str);

                if (removed != null) {
                    output = "[SYSTEM] Removed '" + str + "' from '" + name + "'";
                } else {
                    output = "[ERROR] String '" + str + "' not present in inventory '" + name + "'";
                }

                break;
            }
            case "transfer": {
                if (args.size() != 4) {
                    throw new ActionArgumentException(action, 3);
                }

                String from = args.get(1);
                String to = args.get(2);
                String str = args.get(3);
                Boolean transferred = InventoryActions.transfer(from, to, str);

                if (transferred) {
                    output = "[SYSTEM] Tranferred '" + str + "' from '" + from + "' to '" + to + "'"; 
                } else {
                    output = "[ERROR] Cannot transfer '" + str + "'";
                }

                break;
            }
            case "delete": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                Boolean deleted = InventoryActions.delete(args.get(1));

                if (deleted) {
                    output = "[SYSTEM] Deleted inventory '" + args.get(1) + "'";
                } else {
                    output = "[ERROR] Inventory '" + args.get(1) + "' does not exist";
                }

                break;
            }
            case "clear": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                InventoryActions.clear(args.get(1));

                output = "[SYSTEM] Cleared '" + args.get(1) + "'";
                break;
            }
            case "fill": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                InventoryActions.fill(args.get(1));

                output = "[SYSTEM] Filled '" + args.get(1) + "'";
                break;
            }
            default:
                output = "[ERROR] Invalid argument '" + action + "'";
        }

        return output;
    }
}
