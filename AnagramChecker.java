import java.util.Arrays;
import java.util.Scanner;

public class AnagramChecker {

    public static boolean isAnagram(String s, String t) {
        // Если длины строк разные, это не анаграммы
        if (s.length() != t.length()) {
            return false;
        }

        // Приводим к нижнему регистру и сортируем символы
        char[] sArray = s.toLowerCase().toCharArray();
        char[] tArray = t.toLowerCase().toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // Сравниваем отсортированные массивы
        return Arrays.equals(sArray, tArray);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine();

        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine();

        boolean result = isAnagram(s, t);
        System.out.println("Результат: " + result);

        // Тестирование примеров из задания
        testExamples();

        scanner.close();
    }

    public static void testExamples() {
        System.out.println("\nТестирование примеров:");
        System.out.println("Бейсбол - бобслей: " + isAnagram("Бейсбол", "бобслей"));
        System.out.println("Героин - регион: " + isAnagram("Героин", "регион"));
        System.out.println("Клоака - околка: " + isAnagram("Клоака", "околка"));
    }
}