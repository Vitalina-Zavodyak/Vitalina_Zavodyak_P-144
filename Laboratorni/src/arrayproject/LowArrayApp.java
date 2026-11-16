package arrayproject;

import java.util.Scanner;

public class LowArrayApp {
    public static void main(String[] args) {
        LowArray arr = new LowArray(100); // Створення об'єкта LowArray розміром 100[cite: 3]
        Scanner scanner = new Scanner(System.in);
        
        // Початкове заповнення тестовими даними (як у лабораторній)
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);

        int choice;

        do {
            System.out.println("\n--- МЕНЮ КЕРУВАННЯ МАСИВОМ ---");
            System.out.println("1. Вивести вміст масиву");
            System.out.println("2. Додати елемент");
            System.out.println("3. Пошук елемента за значенням");
            System.out.println("4. Видалити елемент за значенням");
            System.out.println("5. Знайти мінімальний та максимальний елемент");
            System.out.println("6. Підрахувати кількість парних і непарних елементів");
            System.out.println("7. Замінити всі непарні значення на вказане");
            System.out.println("0. Вихід з програми");
            System.out.print("Виберіть дію: ");
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Поточний масив: ");
                    arr.display();
                    break;
                case 2:
                    System.out.print("Введіть число для додавання: ");
                    long valToAdd = scanner.nextLong();
                    arr.insert(valToAdd);
                    System.out.println("Елемент успішно додано.");
                    break;
                case 3:
                    System.out.print("Введіть значення для пошуку: ");
                    long searchKey = scanner.nextLong();
                    if (arr.find(searchKey)) {
                        System.out.println("Знайдено елемент: " + searchKey);
                    } else {
                        System.out.println("Не вдалося знайти елемент: " + searchKey);
                    }
                    break;
                case 4:
                    System.out.print("Введіть значення для видалення: ");
                    long deleteKey = scanner.nextLong();
                    if (arr.delete(deleteKey)) {
                        System.out.println("Елемент " + deleteKey + " успішно видалено.");
                    } else {
                        System.out.println("Елемент " + deleteKey + " не знайдено в масиві.");
                    }
                    break;
                case 5:
                    arr.displayMinMax();
                    break;
                case 6:
                    arr.countEvenOdd();
                    break;
                case 7:
                    System.out.print("Введіть нове значення для заміни непарних елементів: ");
                    long newValue = scanner.nextLong();
                    arr.replaceOdd(newValue);
                    break;
                case 0:
                    System.out.println("Завершення роботи програми.");
                    break;
                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз (від 0 до 7).");
            }
        } while (choice != 0);

        scanner.close();
    }
}