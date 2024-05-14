import java.util.*;

public class Main {
    public static void main(String[] args) {

        int persons = BillCalculator.amountRequest();
        HashMap<ArrayList<String>, Float> finalProductMap = BillCalculator.Calculator();
        Map.Entry<ArrayList<String>, Float> entry = finalProductMap.entrySet().iterator().next();
        ArrayList<String> products = entry.getKey();
        float totalPaid = entry.getValue();

        System.out.println("--------------------");

        System.out.println("Добавленные товары:");
        for (String element : products) {
            System.out.println(element);
        }
        System.out.println("--------------------");

        float payForPerson = totalPaid / persons;
        System.out.printf("Сумма, которую должен заплатить каждый человек, составляет %.2f рубл%s.\n", payForPerson,
                BillCalculator.properTextEnd(payForPerson));

        System.out.println("--------------------");

    }
}

class BillCalculator {


    // Запрос и проверка корректности количества человек
    public static int amountRequest() {
        Scanner scanner = new Scanner(System.in);
        int personsAmount = 0;

        while (personsAmount <= 1) {
            System.out.println("Уточните, пожалуйста, на сколько персон необходимо разделить счет?\nКоличество: ");
            personsAmount = scanner.nextInt();
            if (personsAmount == 1) {
                System.out.println("Тогда нет смысла делить счет :)");
            } else if (personsAmount <= 0) {
                System.out.println("Введено некорректное значение. Попробуйте снова.");
            }
        }
        return personsAmount;
    }


    // Метод getCost для try-catch
    public static float getCost() {
        Scanner scanner = new Scanner(System.in);
        float getCost = -1.0f;
        while (getCost < 0) {
            getCost = scanner.nextFloat();
            if (getCost < 0) {
                System.out.println("Некорректное значение стоимости.\nВведите стоимость товара: ");
            }
        }
        return getCost;
    }


    //Обработка окончаний для вывода
    public static String properTextEnd(float inCost) {
        String textEnd = "";
        int temp = (int)inCost % 10;
        if (temp == 1) {
            textEnd = "ь";
        } else if (temp > 1 & temp < 5) {
            textEnd = "я";
        } else {
            textEnd = "ей";
        }
        return textEnd;
    }


    // Калькулятор товаров
    public static HashMap<ArrayList<String>, Float> Calculator() {

        Scanner scanner = new Scanner(System.in);
        HashMap<ArrayList<String>, Float> productHashMap = new HashMap<>();

        String command = "";
        ArrayList<String> productList = new ArrayList<>();
        float totalCost = 0.0f;
        float productCost = 0.0f;

        while (!command.equals("завершить")) {
            System.out.println("Введите название товара: ");
            String productName = scanner.next();

            //Запрос и проверка ввода стоимости
            while (true) {
                System.out.println("Введите стоимость товара: ");
                try {
                    productCost = getCost();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Некорректное значение стоимости.");
                }
            }

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