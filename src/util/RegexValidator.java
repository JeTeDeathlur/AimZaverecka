package util;

import java.util.regex.Pattern;
/**
 * Utility class to validate nicknames using a regex pattern.
 */
public class RegexValidator {


    /**
     * Validates a nickname against the defined pattern.
     *
     * @param nickname the nickname to validate
     * @return true if the nickname is valid, false otherwise
     */

    private static final Pattern nicknamePattern = Pattern.compile("^[a-zA-Z0-9_]{3,15}$");

    public static boolean isValidNickname(String nickname) {
        return nicknamePattern.matcher(nickname).matches();
    }
}
