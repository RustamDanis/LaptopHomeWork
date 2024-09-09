import java.util.*;

class Laptop {
    private String brand; // бренд
    private int ram; // оперативная память в ГБ
    private int storage; // объем накопителя в ГБ
    private String os; // операционная система
    private String color; // цвет
    private double price; // цена в долларах

    // Конструктор
    public Laptop(String brand, int ram, int storage, String os, String color, double price) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
        this.price = price;
    }

    // Геттеры (методы для получения значений полей)
    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ноутбук [Бренд=" + brand + ", ОЗУ=" + ram + "ГБ, Накопитель=" + storage + "ГБ, ОС=" + os + ", Цвет=" + color + ", Цена=$" + price + "]";
    }



    public static void filterLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>(); // Карта для хранения критериев

        System.out.println("Введите номер, соответствующий нужному критерию:");
        System.out.println("1 - ОЗУ\n2 - Объем накопителя\n3 - Операционная система\n4 - Цвет\n5 - Цена");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Введите минимальный объем ОЗУ (ГБ): ");
                criteria.put("ram", scanner.nextInt());
                break;
            case "2":
                System.out.print("Введите минимальный объем накопителя (ГБ): ");
                criteria.put("storage", scanner.nextInt());
                break;
            case "3":
                System.out.print("Введите операционную систему: ");
                criteria.put("os", scanner.next());
                break;
            case "4":
                System.out.print("Введите цвет: ");
                criteria.put("color", scanner.next());
                break;
            case "5":
                System.out.print("Введите максимальную цену: ");
                criteria.put("price", scanner.nextDouble());
                break;
            default:
                System.out.println("Некорректный выбор.");
                return;
        }

        // Фильтрация ноутбуков по критериям
        laptops.stream()
                .filter(laptop -> {
                    boolean matches = true;
                    if (criteria.containsKey("ram")) matches &= laptop.getRam() >= (int) criteria.get("ram");
                    if (criteria.containsKey("storage"))
                        matches &= laptop.getStorage() >= (int) criteria.get("storage");
                    if (criteria.containsKey("os"))
                        matches &= laptop.getOs().equalsIgnoreCase((String) criteria.get("os"));
                    if (criteria.containsKey("color"))
                        matches &= laptop.getColor().equalsIgnoreCase((String) criteria.get("color"));
                    if (criteria.containsKey("price")) matches &= laptop.getPrice() <= (double) criteria.get("price");
                    return matches;
                })
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 512, "Windows", "Black", 800.0));
        laptops.add(new Laptop("HP", 16, 256, "Windows", "Silver", 950.0));
        laptops.add(new Laptop("Apple", 8, 256, "macOS", "Gray", 1200.0));
        laptops.add(new Laptop("Lenovo", 12, 1024, "Linux", "White", 700.0));

        filterLaptops(laptops);
    }



}