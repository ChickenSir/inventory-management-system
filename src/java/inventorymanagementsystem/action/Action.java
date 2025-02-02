package action;
import java.util.List;

import inventory.InventoryActions;
import inventory.StringAccessException;
import inventory.DuplicateInventoryException;
import inventory.InventoryAccessException;

public interface Action {
    public String run(List<String> args) throws ActionArgumentException, InvalidArgumentException, InventoryAccessException, DuplicateInventoryException, StringAccessException;
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
            throw new ActionArgumentException("list", 0);
        }

        // Display list of all actions with a description
        String output = "=====[List of Actions]=====\n\n"
            + " - list: Lists all actions\n"
            + " - inventory/inv\n"
            + "    - create: Create an inventory with a specified size and name\n"
            + "    - list: List all inventories\n"
            + "    - display: Display the contents of an inventory\n"
            + "    - add: Add a string to an inventory\n"
            + "    - remove: Remove a string from an inventory\n"
            + "    - transfer: Transfer a string between two inventories\n"
            + "    - delete: Delete an inventory with the specified name\n"
            + "    - clear: Clear an inventory\n"
            + "    - fill: Fill an inventory\n"
            + " - exit: Exit the program\n\n"
            + "Entering an action name will show its list of arguments (if any)";
        return output;
    }
}

class InventoryAction implements Action {
    @Override
    public String run(List<String> args) throws ActionArgumentException, InvalidArgumentException, InventoryAccessException, DuplicateInventoryException, StringAccessException {
        if (args.size() < 1) {
            // Display action details if not arguments are given
            return "Inventory:\n"
                    + " - inventory create/list/display/add/remove/transfer/delete/clear/fill\n"
                    + " - inventory create rows columns (optional: name)\n"
                    + " - inventory list\n"
                    + " - inventory display name\n"
                    + " - inventory add string name (optional: row, column)\n"
                    + " - inventory remove string name\n"
                    + " - inventory transfer from to string\n"
                    + " - inventory delete name\n"
                    + " - inventory clear name\n"
                    + " - inventory fill name\n";
        }

        // Get the first argument from the action
        String action = args.get(0);

        String output = "";

        switch (action) {
            case "create": {
                if (args.size() > 4 || args.size() < 3) {
                    throw new ActionArgumentException(action, 3);
                }

                int rows;
                int columns;

                try {
                    // Get rows and column values from arguments
                    rows = Integer.valueOf(args.get(1));
                    columns = Integer.valueOf(args.get(2));
                } catch (NumberFormatException e) {
                    // Values are an invalid type
                    throw new InvalidArgumentException("create");
                }

                String name;
                if (args.size() == 4) {
                    // Get name from arguments
                    name = args.get(3);
                } else {
                    // Generate random name if no name is given
                    name = "Inventory" + (int)(Math.random() * 10000);
                }

                // Create the new inventory
                InventoryActions.create(name, rows, columns);
                output = "[SYSTEM] Succesfully created inventory '" + name + "'";
                
                break;
            }
            case "list": {
                if (args.size() > 1) {
                    throw new ActionArgumentException(action, 0);
                }

                // List all inventories
                output = InventoryActions.list();
                break;
            }
            case "display": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                // Display contents of inventory
                output += "[SYSTEM] Displaying " + args.get(1) + "\n";
                output += InventoryActions.display(args.get(1));
                break;
            }
            case "add": {
                if (args.size() < 3 || args.size() > 5) {
                    throw new ActionArgumentException(action, 4);
                }

                // Get name and string from arguments
                String name = args.get(2);
                String str = args.get(1);

                if (args.size() == 3) {
                    // Add string to current available slot
                    InventoryActions.add(name, str);
                } else {
                    int row;
                    int column;

                    try {
                        // Get row and column values from arguments if present
                        row = Integer.valueOf(args.get(3));
                        column = Integer.valueOf(args.get(4));
                    } catch (NumberFormatException e) {
                        // Values are an invalid type
                        throw new InvalidArgumentException("add");
                    }

                    // Add the string to the inventory
                    InventoryActions.add(name, str, row, column);
                }

                output = "[SYSTEM] Added '" + str + "' to '" + name + "' ";

                break;
            }
            case "remove": {
                if (args.size() != 3) {
                    throw new ActionArgumentException(action, 2);
                }

                // Get name and string from arguments
                String str = args.get(1);
                String name = args.get(2);

                // Remove the string from the inventory
                InventoryActions.remove(name, str);
                output = "[SYSTEM] Removed '" + str + "' from '" + name + "'";

                break;
            }
            case "transfer": {
                if (args.size() != 4) {
                    throw new ActionArgumentException(action, 3);
                }

                // Get names of inventories and string from arguments
                String from = args.get(1);
                String to = args.get(2);
                String str = args.get(3);

                // Transfer string from first inventory to second inventory
                InventoryActions.transfer(from, to, str);
                output = "[SYSTEM] Tranferred '" + str + "' from '" + from + "' to '" + to + "'"; 

                break;
            }
            case "delete": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                // Delete the specified inventory
                InventoryActions.delete(args.get(1));
                output = "[SYSTEM] Deleted inventory '" + args.get(1) + "'";

                break;
            }
            case "clear": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                // Clear the specified inventory
                InventoryActions.clear(args.get(1));
                output = "[SYSTEM] Cleared '" + args.get(1) + "'";

                break;
            }
            case "fill": {
                if (args.size() != 2) {
                    throw new ActionArgumentException(action, 1);
                }

                // Fill the specified inventory
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
