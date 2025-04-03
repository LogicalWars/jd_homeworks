import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");

        System.out.print("Введите размер списка: ");
        int lengthList = scanner.nextInt();
        System.out.print("Введите верхнюю границу для значений: ");
        int maxValue = scanner.nextInt();

        List<Integer> list = createList(lengthList, maxValue);

        System.out.print("Вот случайный список: ");
        showList(list);

        System.out.print("Просим пользователя ввести входные данные для фильтрации: ");
        int filterValue = scanner.nextInt();
        Filter filter = new Filter(filterValue);
        List<Integer> filteredList = filter.filterOut(list);

        logger.log("Выводим результат на экран");
        System.out.print("Вот отфильтрованный список: ");
        showList(filteredList);

        logger.log("Завершаем программу");
    }

    public static List<Integer> createList(int length, int maxValue) {

        Logger logger = Logger.getInstance();
        List<Integer> list = new ArrayList<>(length);
        Random random = new Random();

        logger.log("Создаём и наполняем список");
        for (int i = 0; i < length; i++) {
            list.add(random.nextInt(maxValue));
        }

        return list;
    }

    public static void showList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}