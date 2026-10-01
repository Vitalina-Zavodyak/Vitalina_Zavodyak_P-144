package studentgardes;
import java.util.Scanner;
public class StudentGardes {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Введіть кількість студентів N: ");
    int N = sc.nextInt();

    int склали = 0;
    int неСклали = 0;

    for (int i = 1; i <= N; i++) {
        System.out.print("Введіть оцінку студента " + i + ": ");
        int оцінка = sc.nextInt();

        if (оцінка >= 60) {
            склали++;
        } else {
            неСклали++;
        }
    }

    System.out.println("Кількість студентів, які склали: " + склали);
    System.out.println("Кількість студентів, які не склали: " + неСклали);
}
}