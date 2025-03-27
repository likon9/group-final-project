package Data.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Helps to process user input through console.
 *
 * @author liashevich
 */
public class ManualInputDataHelper {

    /**
     * Collects several user inputs.
     *
     * @param valueToAsk the value to help user to understand what input is expected
     * @return the queue of user inputs
     */
    public List<String> getUserData(String... valueToAsk) {
        List<String> userInputs = new ArrayList<>();
        for (String value : valueToAsk) {
            showMessageToUserInConsole("Please enter the value for: " + value);
            userInputs.add(userInput());
        }
        return userInputs;
    }

    /**
     * Gets user input through console.
     *
     * @return user input
     */
    private String userInput() {
        String userInput = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userInput;
    }

    /**
     * Prints some message to console.
     *
     * @param message the message to print
     */
    public void showMessageToUserInConsole(String message) {
        System.out.println(message);
    }
}
