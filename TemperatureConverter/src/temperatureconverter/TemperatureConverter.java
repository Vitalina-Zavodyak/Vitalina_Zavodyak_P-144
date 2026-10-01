package temperatureconverter;
import java.util.Scanner;
public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть температуру у градусах Цельсія: ");
        double c = scanner.nextDouble();
        double f = c * 9.0 / 5.0 + 32;
        System.out.println("Температура за Фаренгейтом: " + f);
        scanner.close();
    }
}