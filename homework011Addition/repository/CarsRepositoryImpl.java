package homework011Addition.repository;

import homework011Addition.model.Car;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {
    private List<Car> cars = new ArrayList<>();

    @Override
    public List<Car> loadCarsFromFile(String filename) {
        List<Car> loadedCars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String number = parts[0];
                    String model = parts[1];
                    String color = parts[2];
                    long mileage = Long.parseLong(parts[3]);
                    long cost = Long.parseLong(parts[4]);
                    loadedCars.add(new Car(number, model, color, mileage, cost));
                }
            }
            this.cars = loadedCars;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return loadedCars;
    }

    @Override
    public void saveCarsToFile(String filename, List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                writer.println(car.getNumber() + "|" +
                        car.getModel() + "|" +
                        car.getColor() + "|" +
                        car.getMileage() + "|" +
                        car.getCost());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    @Override
    public List<String> findNumbersByColorOrMileage(List<Car> cars, String colorToFind, long mileageToFind) {
        return cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public int countUniqueModelsInPriceRange(List<Car> cars, long minPrice, long maxPrice) {
        return (int) cars.stream()
                .filter(car -> car.getCost() >= minPrice && car.getCost() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
    }

    @Override
    public Optional<String> findColorOfCheapestCar(List<Car> cars) {
        return cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor);
    }

    @Override
    public double calculateAverageCostByModel(List<Car> cars, String modelToFind) {
        return cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }
}
