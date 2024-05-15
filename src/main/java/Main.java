import java.util.*;

public class Main {
    public static void main(String[] args) {

        int persons = BillCalculator.amountRequest();
        HashMap<ArrayList<String>, Float> finalProductMap = BillCalculator.calculator();
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

