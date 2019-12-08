import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write your message bellow: ");

        String userText = reader.readLine();

        char[] text = userText.toCharArray();
        char[] key = new char[text.length];
        char[] encrypted = new char[text.length];
        char[] decrypted = new char[text.length];

        key = generatedKey(text, key);
        encrypted = encryptText(text, key, encrypted);
        decrypted = decryptText(key, encrypted, decrypted);

        System.out.println("User text = " + String.valueOf(text));
        System.out.println("Generated key = " + String.valueOf(key));
        System.out.println("Encrypted text = " + String.valueOf(encrypted));
        System.out.println("Decrypted text = " + String.valueOf(decrypted));
    }

    private static char[] generatedKey(char[] text, char[] key) {
        Random random = new Random();
        for (int i = 0; i < text.length; i++) {
            key[i] = (char) random.nextInt(Character.MAX_VALUE);
        }
        return key;
    }

    private static char[] encryptText(char[] text, char[] key, char[] encrypted) {
        for (int i = 0; i < text.length; i++) {
            encrypted[i] = (char) (text[i] ^ key[i]);
        }
        return encrypted;
    }

    private static char[] decryptText(char[] key, char[] encrypted, char[] decrypted) {
        for (int i = 0; i < key.length; i++) {
            decrypted[i] = (char) (encrypted[i] ^ key[i]);
        }
        return decrypted;
    }
}