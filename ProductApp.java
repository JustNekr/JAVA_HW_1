import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Person {
    private String name;
    private int money;
    private List<Product> bag;

    public Person(String name, int money) {
        setName(name);
        setMoney(money);
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getBag() {
        return bag;
    }

    public boolean canAfford(Product product) {
        return money >= product.getPrice();
    }

    public void buyProduct(Product product) {
        if (canAfford(product)) {
            bag.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return money == person.money && Objects.equals(name, person.name) && Objects.equals(bag, person.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, bag);
    }

    @Override
    public String toString() {
        if (bag.isEmpty()) {
            return name + " - Ничего не куплено";
        }
        return name + " - " + String.join(", ", bag.stream().map(Product::getName).toArray(String[]::new));
    }
}

public class ProductApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Создаем покупателей
            System.out.println("Введите покупателей в формате: Имя = Сумма");
            List<Person> people = new ArrayList<>();
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                try {
                    String[] parts = input.split("=");
                    String name = parts[0].trim();
                    int money = Integer.parseInt(parts[1].trim());
                    people.add(new Person(name, money));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            // Создаем продукты
            System.out.println("Введите продукты в формате: Название = Цена");
            List<Product> products = new ArrayList<>();
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                try {
                    String[] parts = input.split("=");
                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    products.add(new Product(name, price));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            // Процесс покупки
            System.out.println("Введите покупки в формате: Имя - Продукт (или END для завершения)");
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("END")) break;

                try {
                    String[] parts = input.split("-");
                    String personName = parts[0].trim();
                    String productName = parts[1].trim();

                    Person person = people.stream()
                            .filter(p -> p.getName().equals(personName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Покупатель не найден"));

                    Product product = products.stream()
                            .filter(p -> p.getName().equals(productName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));

                    person.buyProduct(product);
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            // Вывод результатов
            for (Person person : people) {
                System.out.println(person);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}