import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import action.Action;
import action.ActionArgumentException;
import action.ActionList;
import action.InvalidArgumentException;
import inventory.InventoryAccessException;
import inventory.StringAccessException;

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Console console = System.console();
        ActionList actionList = ActionList.getInstance();

        System.out.println("Type 'list' for a list of actions");

        while(true) {
            // Get input from the user
            System.out.print("> ");
            String input = console.readLine();

            // Split input into action name and arguments
            List<String> action = Arrays.asList(input.split(" ", 2));
            List<String> actionArgs = (action.size() != 1) ? Arrays.asList(action.get(1).split(" ")) : new ArrayList<String>();

            // Get action from action list
            Action actionType = actionList.getAction(action.get(0));

            try {
                // Run action from action list
                System.out.println(actionType.run(actionArgs));
            } catch (ActionArgumentException | InvalidArgumentException | InventoryAccessException | StringAccessException e) {
                // Display error messages
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("[ERROR] Unknown action '" + action.get(0) + "'. Enter 'list' for a list of actions.");
            }
        }
    }
}
