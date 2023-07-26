import PasswordGenerator.java;

public class PasswordGenerator {
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int passwordLength = 12; // Change this value to adjust the password length
        String generatedPassword = generatePassword(passwordLength);
        System.out.println("Generated Password: " + generatedPassword);
    }

    private static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length should be at least 8 characters.");
        }

        StringBuilder passwordBuilder = new StringBuilder();
        int typesCount = 4;
        int charsPerType = length / typesCount;

        // Ensure at least one character from each type
        passwordBuilder.append(getRandomChar(UPPERCASE_CHARS));
        passwordBuilder.append(getRandomChar(LOWERCASE_CHARS));
        passwordBuilder.append(getRandomChar(NUMBERS));
        passwordBuilder.append(getRandomChar(SPECIAL_CHARS));

        // Fill up the remaining characters randomly
        for (int i = 0; i < length - typesCount; i++) {
            String charSet = UPPERCASE_CHARS + LOWERCASE_CHARS + NUMBERS + SPECIAL_CHARS;
            passwordBuilder.append(getRandomChar(charSet));
        }

        // Shuffle the password to randomize the positions of the required characters
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
