package myfirstapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFirstApplication {
    
    // Метод для зчитування рядка з клавіатури
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    // Метод для зчитування дійсного числа (float)
    public static float getFloat() throws IOException {
        String s = getString();
        return Float.parseFloat(s);
    }

    // Метод для зчитування цілого числа (int) - потрібно для завдання 3
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    // Методи для завдання 4 (окремі математичні операції)
    public static float sum(float x, float y) {
        return x + y;
    }

    public static float dif(float x, float y) {
        return x - y;
    }

    public static float multi(float x, float y) {
        return x * y;
    }

    public static float div(float x, float y) {
        if (y == 0) {
            System.out.println("Помилка: ділення на нуль!");
            return 0;
        }
        return x / y;
    }

    public static void main(String[] args) throws IOException {
        // --- ЗАВДАННЯ 1: Введення дійсних чисел, множення та ділення ---
        System.out.println("=== ЗАВДАННЯ 1: Множення та ділення дійсних чисел ===");
        System.out.print("Введіть перше число (a1): ");
        float a1 = getFloat();
        
        System.out.print("Введіть друге число (a2): ");
        float a2 = getFloat();
        
        float multiplication = multi(a1, a2);
        System.out.println("Результат множення (multi): " + multiplication);
        
        if (a2 != 0) {
            float division = div(a1, a2);
            System.out.println("Результат ділення (div): " + division);
        } else {
            System.out.println("Результат ділення: неможливо поділити на нуль.");
        }

        // --- ЗАВДАННЯ 2: Порівняння двох чисел за допомогою if ---
        System.out.println("\n=== ЗАВДАННЯ 2: Порівняння чисел ===");
        if (a1 > a2) {
            System.out.println("Число " + a1 + " більше за " + a2);
        } else if (a1 < a2) {
            System.out.println("Число " + a1 + " менше за " + a2);
        } else {
            System.out.println("Числа рівні між собою.");
        }

        // --- ЗАВДАННЯ 3: Використання циклу for (з цілими числами) ---
        System.out.println("\n=== ЗАВДАННЯ 3: Робота циклу for з цілими числами ===");
        System.out.print("Введіть ціле число для суми (згідно з умовою 10): ");
        int cSum = getInt();
        
        System.out.print("Введіть ціле число для різниці (згідно з умовою 5): ");
        int cDiff = getInt();

        System.out.println("Збільшення суми (10 разів) та різниці (5 разів):");
        for (int i = 1; i <= 10; i++) {
            cSum += 2; // звичайна імітація збільшення
            if (i <= 5) {
                cDiff += 2;
            }
            System.out.println("Ітерація " + i + ": сума = " + cSum + ", різниця = " + cDiff);
        }

        // --- ЗАВДАННЯ 4: Перевірка окремих методів (sum, dif, multi, div) ---
        System.out.println("\n=== ЗАВДАННЯ 4: Перевірка окремих методів ===");
        System.out.println("Сума (sum): " + sum(a1, a2));
        System.out.println("Різниця (dif): " + dif(a1, a2));
        System.out.println("Множення (multi): " + multi(a1, a2));
        System.out.println("Ділення (div): " + div(a1, a2));
    }
}