package bubblesort; // або ваша назва пакета

public class ArrayBub {
    private long[] a; // Посилання на масив a[cite: 8]
    private int nElems; // Кількість елементів даних[cite: 8]

    // Конструктор
    public ArrayBub(int max) {
        a = new long[max]; // Створення масиву[cite: 8]
        nElems = 0; // Поки немає жодного елемента[cite: 8]
    }

    // Вставка елемента в масив[cite: 8]
    public void insert(long value) {
        a[nElems] = value; // Власне вставка[cite: 8]
        nElems++; // Збільшення розміру[cite: 8]
    }

    // Вивід вмісту масиву[cite: 8]
    public void display() {
        for(int j = 0; j < nElems; j++) { // Для кожного елемента[cite: 8]
            System.out.print(a[j] + " "); // Вивід[cite: 8]
        }
        System.out.println("");
    }

    // Бульбашкове сортування (Завдання 3: з вибором напрямку сортування)
    public void bubbleSort(boolean ascending) {
        int out, in;
        for(out = nElems - 1; out > 0; out--) {
            for(in = 0; in < out; in++) {
                if(ascending) {
                    if(a[in] > a[in + 1]) swap(in, in + 1); // за зростанням
                } else {
                    if(a[in] < a[in + 1]) swap(in, in + 1); // за спаданням
                }
            }
        }
    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
}