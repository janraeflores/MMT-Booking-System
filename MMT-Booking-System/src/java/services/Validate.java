
package services;

/**
 *
 * @author Joanna
 */
public class Validate {
    public static boolean isEmpty(String[] input) {
        for (String s : input) {
            if (s.equals("")) {
                return false;
            }
        }
        return true;
    }
}
