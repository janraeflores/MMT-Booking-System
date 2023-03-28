
package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Joanna
 */
public class Validate {
    /**
     * Tests an array of input fields if an input is empty or null
     * @param input as an array of inputs
     * @return true if any of the fields contained in the array are empty, otherwise, returns false
     */
    public static boolean isEmpty(String[] input) {
        try {
            for (String s : input) {
                if (!s.equals("")) {
                    return false;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
    /**
     * Validates a password, on registration, that meets the requirements expressed in the regex pattern, PASSWORD_PATTERN
     * @param password
     * @return true if the password meets the requirements of PASSWORD_PATTERN otherwise, returns false
     */
    public static boolean passwordRequirement(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean isValid = false;
        
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
            
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
