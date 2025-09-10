import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestPowerfulSet {
    public static void main(String[] args) {
        PowerfulSet powerfulSet = new PowerfulSet();

        // Тестовые данные
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(0, 1, 2, 4));

        Set<String> strSet1 = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        Set<String> strSet2 = new HashSet<>(Arrays.asList("banana", "grape", "kiwi"));

        System.out.println("=== ТЕСТИРОВАНИЕ PowerfulSet ===");

        // Тестирование с целыми числами
        System.out.println("\n1. Тестирование с числами:");
        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);

        Set<Integer> intersection = powerfulSet.intersection(set1, set2);
        Set<Integer> union = powerfulSet.union(set1, set2);
        Set<Integer> complement = powerfulSet.relativeComplement(set1, set2);

        System.out.println("Пересечение: " + intersection);
        System.out.println("Объединение: " + union);
        System.out.println("Относительное дополнение (set1 \\ set2): " + complement);

        // Тестирование со строками
        System.out.println("\n2. Тестирование со строками:");
        System.out.println("strSet1 = " + strSet1);
        System.out.println("strSet2 = " + strSet2);

        Set<String> strIntersection = powerfulSet.intersection(strSet1, strSet2);
        Set<String> strUnion = powerfulSet.union(strSet1, strSet2);
        Set<String> strComplement = powerfulSet.relativeComplement(strSet1, strSet2);

        System.out.println("Пересечение: " + strIntersection);
        System.out.println("Объединение: " + strUnion);
        System.out.println("Относительное дополнение (strSet1 \\ strSet2): " + strComplement);
    }
}