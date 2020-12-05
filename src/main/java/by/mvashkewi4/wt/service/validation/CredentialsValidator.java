package by.mvashkewi4.wt.service.validation;

import java.util.regex.Pattern;

public class CredentialsValidator {
    private static Pattern containsDigitPattern = Pattern.compile(".*\\d.*");
    private static Pattern lettersDigitsUnderscorePattern = Pattern.compile("^[a-zA-Z0-9_]*$");

    public static boolean isCorrect(String login, String password) {
        return isLoginCorrect(login) && isPasswordCorrect(login);
    }

    private static boolean isLoginCorrect(String login) {
        return login.length() > 6;
    }

    private static boolean isPasswordCorrect(String password) {
        return password.length() > 6
                && lettersDigitsUnderscorePattern.matcher(password).matches()
                && containsDigitPattern.matcher(password).matches();
    }
}
