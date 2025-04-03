import java.util.ArrayList;
import java.util.List;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
    }

    public List<Integer> filterOut(List<Integer> source) {
        Logger logger = Logger.getInstance();
        List<Integer> result = new ArrayList<>();
        int count = 0;

        logger.log("Запускаем фильтрацию");
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i) > treshold) {
                result.add(source.get(i));
                logger.log("Элемент \"%d\" проходит".formatted(source.get(i)));
                count++;
            }
            logger.log("Элемент \"%d\" не проходит".formatted(source.get(i)));
        }
        logger.log("Прошло фильтр %d элемента из %d".formatted(count, source.size()));
        return result;
    }
}