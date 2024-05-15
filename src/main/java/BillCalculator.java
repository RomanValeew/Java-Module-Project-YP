import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BillCalculator {

    static Scanner scanner = new Scanner(System.in);


    // Запрос и проверка корректности количества человек
    public static int amountRequest() {
        int amountOfPeople = 0;
        System.out.println("Уточните, пожалуйста, на сколько персон необходимо разделить счет?\nКоличество: ");

        while (amountOfPeople <= 1) {
            try {
                amountOfPeople = scanner.nextInt();
                if (amountOfPeople == 1) {
                    System.out.println("Тогда нет смысла делить счет:). Попробуйте снова.");
                } else if (amountOfPeople <= 0) {
                    System.out.println("Введено некорректное значение. Попробуйте снова.");
                }
            } catch  (InputMismatchException e) {
                System.out.println("Необходимо ввести число. Попробуйте снова.");
                scanner.next();
            }
        }
        return amountOfPeople;
    }


    // Запрос и проверка правильности стоимости
    public static float getCost() {
        float getCost = -1.0f;

        while (getCost < 0) {
            System.out.println("Введите стоимость товара: ");
            try {
                getCost = scanner.nextFloat();
                if (getCost < 0) {
                    System.out.println("Некорректное значение стоимости.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Необходимо ввести число. Попробуйте снова.");
                scanner.next();
            }
        }
        return getCost;
    }


    //Обработка окончаний для вывода
    public static String properTextEnd(float inCost) {
        String textEnd = "";
        int temp = (int)inCost;

        if (temp % 10 == 1 & !((temp % 100) > 10 & (temp % 100) < 20)) {
            textEnd = "ь";
        } else if ((temp % 10) > 1 & (temp % 10) < 5 & temp > 100) {
            textEnd = "я";
        } else if ((temp % 100) > 10 & (temp % 100) < 20){
            textEnd = "ей";
        } else {
            textEnd = "ей";
        }
        return textEnd;
    }


    // Калькулятор товаров
    public static HashMap<ArrayList<String>, Float> calculator() {

        HashMap<ArrayList<String>, Float> productHashMap = new HashMap<>();

        String command = "";
        ArrayList<String> productList = new ArrayList<>();
        float totalCost = 0.0f;
        float productCost = 0.0f;

        while (!command.equals("завершить")) {
            System.out.println("Введите название товара: ");
            String productName = scanner.next();

            productCost = getCost();

            productList.add(productName);
            totalCost = totalCost + productCost;
            System.out.printf("Товар '%s' стоимостью %.2f рубл%s успешно добавлен.\n", productName,
                    productCost, properTextEnd(productCost));

            System.out.println("Хотите добавить еще товар? (Для завершения введите команду 'Завершить')");
            command = scanner.next().toLowerCase();
        }
        productHashMap.put(productList, totalCost);
        return productHashMap;
    }
}
