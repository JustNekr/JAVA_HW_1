import java.util.Arrays;
// import java.util.Scanner;

public class SortAndLower {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);


//        System.out.print("Введите два слова через пробел: ");
//        String input = scanner.nextLine();


        String input = "Hello world";
        String[] words = input.split(" ");

        for (int i = 0; i < words.length; i++) {
            String lowerWord = words[i].toLowerCase();

            char[] chars = lowerWord.toCharArray();
            Arrays.sort(chars);

            words[i] = new String(chars);
        }


        System.out.println("Результат: " + words[0] + " " + words[1]);
    }
}