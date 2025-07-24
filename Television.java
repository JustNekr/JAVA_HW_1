import java.util.Random;

public class Television {
    // Приватные поля класса
    private String brand;
    private String model;
    private double screenSize;
    private boolean isOn;
    private int currentChannel;
    private int currentVolume;

    // Конструктор с параметрами
    public Television(String brand, String model, double screenSize) {
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.isOn = false; // По умолчанию выключен
        this.currentChannel = 1; // Стандартный канал
        this.currentVolume = 50; // Средняя громкость
    }

    // Конструктор для случайных значений
    public Television() {
        Random rand = new Random();
        String[] brands = {"Samsung", "LG", "Sony", "Panasonic", "Toshiba"};
        String[] models = {"SmartTV-2023", "UltraHD", "Crystal", "Bravia", "Viera"};

        this.brand = brands[rand.nextInt(brands.length)];
        this.model = models[rand.nextInt(models.length)];
        this.screenSize = 20 + rand.nextDouble() * 60; // 20-80 дюймов
        this.isOn = rand.nextBoolean();
        this.currentChannel = 1 + rand.nextInt(100); // 1-100 канал
        this.currentVolume = rand.nextInt(101); // 0-100 громкость
    }

    // Геттеры
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getScreenSize() { return screenSize; }
    public boolean isOn() { return isOn; }
    public int getCurrentChannel() { return currentChannel; }
    public int getCurrentVolume() { return currentVolume; }

    // Сеттеры с валидацией
    public void setCurrentChannel(int channel) {
        if (channel >= 1 && channel <= 100) {
            currentChannel = channel;
        } else {
            System.out.println("Недопустимый канал (1-100)");
        }
    }

    public void setCurrentVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            currentVolume = volume;
        } else {
            System.out.println("Недопустимая громкость (0-100)");
        }
    }

    // Методы управления
    public void powerOn() { isOn = true; }
    public void powerOff() { isOn = false; }
    public void channelUp() { setCurrentChannel(currentChannel + 1); }
    public void channelDown() { setCurrentChannel(currentChannel - 1); }
    public void volumeUp() { setCurrentVolume(currentVolume + 5); }
    public void volumeDown() { setCurrentVolume(currentVolume - 5); }

    @Override
    public String toString() {
        return String.format(
                "Телевизор %s %s (%.1f\")\nСостояние: %s\nКанал: %d\nГромкость: %d",
                brand, model, screenSize,
                isOn ? "ВКЛ" : "ВЫКЛ",
                currentChannel, currentVolume
        );
    }
}