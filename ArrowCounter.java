import java.util.Random;
import java.util.Scanner;

public class ArrowCounter {

    // Метод генерации случайной строки из символов '>', '<', '-'
    public static String generateRandomSequence(int length) {
        String symbols = "><-";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(symbols.length());
            char randomChar = symbols.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    // Метод для подсчёта стрелок
    public static int countArrows(String sequence) {
        int count = 0;

        for (int i = 0; i <= sequence.length() - 5; i++) {
            String substring = sequence.substring(i, i + 5);
            if (substring.equals(">>-->") || substring.equals("<--<<")) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int min = 5;
        int max = 106;
        int length = random.nextInt(max - min + 1) + min;

        // Генерируем строку
        String sequence = generateRandomSequence(length);
        System.out.println("Сгенерированная строка: " + sequence);

        // Считаем стрелки
        int arrowsCount = countArrows(sequence);
        System.out.println("Найдено стрелок: " + arrowsCount);

    }
}