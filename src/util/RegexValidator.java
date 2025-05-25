package util;

import java.util.regex.Pattern;

public class RegexValidator {
    private static final Pattern nicknamePattern = Pattern.compile("^[a-zA-Z0-9_]{3,15}$");

    public static boolean isValidNickname(String nickname) {
        return nicknamePattern.matcher(nickname).matches();
    }
}
