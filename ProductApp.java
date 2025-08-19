import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class BaseProduct {
    private String name;
    private int price;

    public BaseProduct(String name, int price) {
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
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("Имя не может содержать только цифры");
        }
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть положительной");
        }
        this.price = price;
    }

    public abstract int getActualPrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return price == that.price && Objects.equals(name, that.name);
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

class Product extends BaseProduct {
    public Product(String name, int price) {
        super(name, price);
    }

    @Override
    public int getActualPrice() {
        return getPrice();
    }
}

class DiscountProduct extends BaseProduct {
    private int discount;
    private LocalDate discountEndDate;

    public DiscountProduct(String name, int price, int discount, LocalDate discountEndDate) {
        super(name, price);
        setDiscount(discount);
        setDiscountEndDate(discountEndDate);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        if (discount < 0) {
            throw new IllegalArgumentException("Скидка не может быть отрицательной");
        }
        if (discount >= getPrice()) {
            throw new IllegalArgumentException("Скидка не может быть больше или равна цене продукта");
        }
        this.discount = discount;
    }

    public LocalDate getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(LocalDate discountEndDate) {
        if (discountEndDate == null) {
            throw new IllegalArgumentException("Дата окончания скидки не может быть пустой");
        }
        this.discountEndDate = discountEndDate;
    }

    public boolean isDiscountActive() {
        return LocalDate.now().isBefore(discountEndDate) || LocalDate.now().isEqual(discountEndDate);
    }

    @Override
    public int getActualPrice() {
        if (isDiscountActive()) {
            return getPrice() - discount;
        }
        return getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return discount == that.discount && Objects.equals(discountEndDate, that.discountEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount, discountEndDate);
    }

    @Override
    public String toString() {
        String discountInfo = isDiscountActive() ?
                " (скидка " + discount + " руб., действует до " + discountEndDate + ")" :
                " (скидка недействительна)";
        return super.toString() + discountInfo;
    }
}

class Person {
    private String name;
    private int money;
    private List<BaseProduct> bag;

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

    public List<BaseProduct> getBag() {
        return bag;
    }

    public boolean canAfford(BaseProduct product) {
        return money >= product.getActualPrice();
    }

    public void buyProduct(BaseProduct product) {
        int actualPrice = product.getActualPrice();
        if (canAfford(product)) {
            bag.add(product);
            money -= actualPrice;
            System.out.println(name + " купил " + product.getName() + " за " + actualPrice + " руб.");
        } else {
            System.out.println(name + " не может позволить себе " + product.getName() + " (нужно: " + actualPrice + " руб., есть: " + money + " руб.)");
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
        return name + " - " + String.join(", ", bag.stream().map(BaseProduct::getName).toArray(String[]::new));
    }
}

public class ProductApp {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                    System.out.println("Ошибка: " + e.getMessage());
                    return;
                }
            }

            // Создаем продукты
            System.out.println("Введите продукты в формате: Название = Цена [= Скидка = Дата_окончания_скидки(dd.MM.yyyy)]");
            System.out.println("Для обычного продукта: Хлеб = 50");
            System.out.println("Для скидочного продукта: Молоко = 80 = 20 = 25.12.2024");
            List<BaseProduct> products = new ArrayList<>();
            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                try {
                    String[] parts = input.split("=");
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Неверный формат ввода продукта");
                    }

                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());

                    if (parts.length == 2) {
                        // Обычный продукт
                        products.add(new Product(name, price));
                    } else if (parts.length == 4) {
                        // Скидочный продукт
                        int discount = Integer.parseInt(parts[2].trim());
                        LocalDate endDate = LocalDate.parse(parts[3].trim(), DATE_FORMATTER);
                        products.add(new DiscountProduct(name, price, discount, endDate));
                    } else {
                        throw new IllegalArgumentException("Неверное количество параметров для продукта");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Ошибка: Неверный формат даты. Используйте dd.MM.yyyy");
                    return;
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
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
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Неверный формат покупки");
                    }

                    String personName = parts[0].trim();
                    String productName = parts[1].trim();

                    Person person = people.stream()
                            .filter(p -> p.getName().equals(personName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Покупатель не найден"));

                    BaseProduct product = products.stream()
                            .filter(p -> p.getName().equals(productName))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));

                    person.buyProduct(product);
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

            // Вывод результатов
            System.out.println("\nРезультаты покупок:");
            for (Person person : people) {
                System.out.println(person);
            }

            // Вывод информации о продуктах
            System.out.println("\nИнформация о продуктах:");
            for (BaseProduct product : products) {
                if (product instanceof DiscountProduct) {
                    DiscountProduct dp = (DiscountProduct) product;
                    System.out.println(dp.getName() + " - базовая цена: " + dp.getPrice() +
                            " руб., текущая цена: " + dp.getActualPrice() +
                            " руб., скидка: " + (dp.isDiscountActive() ? "действует" : "недействительна"));
                } else {
                    System.out.println(product.getName() + " - цена: " + product.getActualPrice() + " руб.");
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}