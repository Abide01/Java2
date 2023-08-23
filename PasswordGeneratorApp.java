import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGeneratorApp {
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static SecureRandom random = new SecureRandom();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Password Generator!");
        System.out.println("Choose the password length:");
        System.out.println("1. Short Password (8 characters)");
        System.out.println("2. Long Password (16 characters)");

        int choice = scanner.nextInt();
        int passwordLength = (choice == 1) ? 8 : 16;

        String generatedPassword = generatePassword(passwordLength);
        System.out.println("Generated Password: " + generatedPassword);

        // Close the scanner
        scanner.close();
    }

    private static String generatePassword(int length) {
        StringBuilder passwordBuilder = new StringBuilder();
        int typesCount = 4;
        int charsPerType = length / typesCount;

        passwordBuilder.append(getRandomChar(UPPERCASE_CHARS));
        passwordBuilder.append(getRandomChar(LOWERCASE_CHARS));
        passwordBuilder.append(getRandomChar(NUMBERS));
        passwordBuilder.append(getRandomChar(SPECIAL_CHARS));

        String charSet = UPPERCASE_CHARS + LOWERCASE_CHARS + NUMBERS + SPECIAL_CHARS;
        for (int i = 0; i < length - typesCount; i++) {
            passwordBuilder.append(getRandomChar(charSet));
        }

        char[] passwordChars = passwordBuilder.toString().toCharArray();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[randomIndex];
            passwordChars[randomIndex] = temp;
        }

        return new String(passwordChars);
    }

    private static char getRandomChar(String charSet) {
        int index = random.nextInt(charSet.length());
        return charSet.charAt(index);
    }
}
