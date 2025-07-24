import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создание телевизора через ввод пользователя
        System.out.println("\n=== Создание телевизора через ввод ===");
        System.out.print("Бренд: ");
        String brand = scanner.nextLine();
        System.out.print("Модель: ");
        String model = scanner.nextLine();
        System.out.print("Диагональ (дюймы): ");
        double size = scanner.nextDouble();

        Television userTV = new Television(brand, model, size);
        System.out.println("\nСоздан телевизор:");
        System.out.println(userTV);

        // Создание телевизора со случайными параметрами
        System.out.println("\n=== Телевизор со случайными параметрами ===");
        Television randomTV = new Television();
        System.out.println(randomTV);

        // Демонстрация управления
        System.out.println("\n=== Управление телевизором ===");
        randomTV.powerOn();
        randomTV.setCurrentChannel(15);
        randomTV.volumeUp();
        randomTV.volumeUp();

        System.out.println("\nПосле включения и настройки:");
        System.out.println(randomTV);

        scanner.close();
    }
}