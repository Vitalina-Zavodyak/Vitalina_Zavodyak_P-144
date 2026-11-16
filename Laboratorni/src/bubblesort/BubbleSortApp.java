package bubblesort; // або ваша назва пакета

import java.util.Scanner;

public class BubbleSortApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введіть максимальний розмір масиву: ");
        int maxSize = scanner.nextInt();
        
        ArrayBub arr = new ArrayBub(maxSize);

        System.out.print("Скільки випадкових елементів згенерувати? ");
        int nElemsToFill = scanner.nextInt();

        // Заповнення масиву випадковими числами за методичкою
        for (int i = 0; i < nElemsToFill; i++) {
            long n = (long) (java.lang.Math.random() * (maxSize - 1));
            arr.insert(n);
        }

        System.out.print("До сортування: ");
        arr.display();

        // Сортування за зростанням (true) або спаданням (false)
        arr.bubbleSort(true); 

        System.out.print("Після сортування за зростанням: ");
        arr.display();
        
        scanner.close();
    }
}