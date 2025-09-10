import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniqueElements {

    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }

    // Пример использования
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);
        numbers.add(4);

        Set<Integer> uniqueNumbers = getUniqueElements(numbers);
        System.out.println("Исходный список: " + numbers);
        System.out.println("Уникальные элементы: " + uniqueNumbers);

        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");
        words.add("orange");

        Set<String> uniqueWords = getUniqueElements(words);
        System.out.println("Исходный список: " + words);
        System.out.println("Уникальные элементы: " + uniqueWords);
    }
}