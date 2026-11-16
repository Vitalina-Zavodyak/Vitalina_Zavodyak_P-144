package arrayproject;

public class LowArray {
    private long[] a; // Посилання на масив[cite: 3]
    private int nElems; // Кількість елементів у масиві

    // Конструктор класу
    public LowArray(int size) {
        a = new long[size]; // Створення масиву заданого розміру[cite: 3, 5]
        nElems = 0;
    }

    // Метод для додавання елементів
    public void insert(long value) {
        if (nElems < a.length) {
            a[nElems] = value;
            nElems++;
        } else {
            System.out.println("Масив переповнений!");
        }
    }

    // 1. Метод для пошуку елементів в масиві за заданим значенням[cite: 5]
    public boolean find(long searchKey) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] == searchKey) {
                break;
            }
        }
        return j != nElems; // Якщо не дійшли до кінця, значить знайшли
    }

    // 2. Метод для видалення значень в масиві за заданим значенням[cite: 5]
    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] == value) {
                break;
            }
        }
        if (j == nElems) {
            return false; // Елемент не знайдено
        } else {
            for (int k = j; k < nElems - 1; k++) {
                a[k] = a[k + 1]; // Зсув елементів зліва направо[cite: 4]
            }
            nElems--; // Зменшення розміру масиву[cite: 4]
            return true;
        }
    }

    // 3. Метод для виведення вмісту масиву[cite: 5]
    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    // 4. Метод для знаходження максимального і мінімального елемента в масиві[cite: 5]
    public void displayMinMax() {
        if (nElems == 0) {
            System.out.println("Масив порожній.");
            return;
        }
        long min = a[0];
        long max = a[0];

        for (int i = 1; i < nElems; i++) {
            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > max) {
                max = a[i];
            }
        }

        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
    }

    // 5. Метод для підрахунку кількості парних і непарних значень[cite: 5]
    public void countEvenOdd() {
        int evenCount = 0;
        int oddCount = 0;

        for (int j = 0; j < nElems; j++) {
            if (a[j] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println("Кількість парних значень: " + evenCount);
        System.out.println("Кількість непарних значень: " + oddCount);
    }

    // 6. Метод для заміни всіх непарних значень масиву на вказане значення[cite: 5]
    public void replaceOdd(long newValue) {
        for (int j = 0; j < nElems; j++) {
            if (a[j] % 2 != 0) {
                a[j] = newValue;
            }
        }
        System.out.println("Усі непарні значення успішно замінено на " + newValue);
    }
}