import java.util.Scanner;

public class KeyboardLeftLetter {
    public static void main(String[] args) {

        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        Scanner scanner = new Scanner(System.in);
        char inputChar = scanner.next().charAt(0);
        inputChar = Character.toLowerCase(inputChar);

        int index = keyboard.indexOf(inputChar);
        if (index == -1) {
            System.out.println("Введен недопустимый символ");
            return;
        }

        int leftIndex = index - 1;
        if (leftIndex < 0) {
            leftIndex = keyboard.length() - 1;
        }
        char leftChar = keyboard.charAt(leftIndex);

        System.out.println(leftChar);
    }
}
