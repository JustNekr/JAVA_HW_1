import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Random random = new Random();

        // Генерация выбора Васи (0-камень, 1-ножницы, 2-бумага)
        int vasyaChoice = random.nextInt(3);

        // Генерация выбора Пети
        int petyaChoice = random.nextInt(3);

        System.out.println("Вася выбрал: " + getChoiceName(vasyaChoice));
        System.out.println("Петя выбрал: " + getChoiceName(petyaChoice));

        // Определение победителя
        if (vasyaChoice == petyaChoice) {
            System.out.println("Ничья!");
        } else if ((vasyaChoice == 0 && petyaChoice == 1) ||
                (vasyaChoice == 1 && petyaChoice == 2) ||
                (vasyaChoice == 2 && petyaChoice == 0)) {
            System.out.println("Вася выиграл!");
        } else {
            System.out.println("Петя выиграл!");
        }
    }

    // Метод для преобразования числового выбора в название
    private static String getChoiceName(int choice) {
        return switch (choice) {
            case 0 -> "камень";
            case 1 -> "ножницы";
            case 2 -> "бумага";
            default -> "неизвестно";
        };
    }
}