package homework011Addition.test;

import homework011Addition.model.Car;
import homework011Addition.repository.CarsRepository;
import homework011Addition.repository.CarsRepositoryImpl;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CarsRepository repository = new CarsRepositoryImpl();

        // Загрузка данных из файла
        List<Car> cars = repository.loadCarsFromFile("homework011Addition/data/cars.txt");

        // Вывод всех автомобилей
        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println();

        // 1) Номера автомобилей по цвету или пробегу
        String colorToFind = "Black";
        long mileageToFind = 0L;
        List<String> numbers = repository.findNumbersByColorOrMileage(cars, colorToFind, mileageToFind);
        System.out.println("Номера автомобилей по цвету или пробегу: " + String.join(" ", numbers));

        // 2) Количество уникальных моделей в ценовом диапазоне
        long minPrice = 700000L;
        long maxPrice = 800000L;
        int uniqueModelsCount = repository.countUniqueModelsInPriceRange(cars, minPrice, maxPrice);
        System.out.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

        // 3) Цвет автомобиля с минимальной стоимостью
        Optional<String> cheapestColor = repository.findColorOfCheapestCar(cars);
        System.out.println("Цвет автомобиля с минимальной стоимостью: " +
                cheapestColor.orElse("Не найдено"));

        // 4) Средняя стоимость моделей
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        double avgCost1 = repository.calculateAverageCostByModel(cars, modelToFind1);
        double avgCost2 = repository.calculateAverageCostByModel(cars, modelToFind2);

        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind1, avgCost1);
        System.out.printf("Средняя стоимость модели %s: %.2f%n", modelToFind2, avgCost2);

        // Сохранение результатов в файл
        repository.saveCarsToFile("homework011Addition/data/output.txt", cars);
    }
}
