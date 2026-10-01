package weektemperature;
import java.util.Scanner;
public class WeekTemperature {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cold = 0;
        int normal = 0;
        int hot = 0;
        for (int day = 1; day <= 7; day++) {
            System.out.print("Температура за день " + day + ": ");
            double temp = scanner.nextDouble();
            if (temp < 0) {
                cold++;
            } else if (temp <= 20) {
                normal++;
            } else {
                hot++;
            }
        }
        System.out.println("Днів нижче 0 °C: " + cold);
        System.out.println("Днів від 0 до 20 °C: " + normal);
        System.out.println("Днів вище 20 °C: " + hot);
        scanner.close();
    }
}
