import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String userName = scanner.nextLine();

        System.out.println("Привет, " + userName);

        scanner.close();
    }
}