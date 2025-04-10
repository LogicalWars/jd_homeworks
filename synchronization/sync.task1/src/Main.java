import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    static final int countThreads = 1000;
    static final ExecutorService executer = Executors.newFixedThreadPool(countThreads);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<?>> futures = new ArrayList<>();
        Thread thread = new Thread(() -> {
            synchronized (sizeToFreq) {
                while (!Thread.interrupted()) {
                    try {
                        sizeToFreq.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                    sizeToFreq.entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .ifPresent(entry -> System.out.printf(
                                            "Лидер повторений: %d (встретилось %d раз)%n",
                                            entry.getKey(), entry.getValue()));
                }
            }
        });
        thread.start();

        for (int i = 0; i < countThreads; i++) {
            Future<?> future = executer.submit(() -> {
                synchronized (sizeToFreq) {
                    int result = calculateRepeatOfR(generateRoute("RLRFR", 100), 'R');
                    sizeToFreq.put(result, sizeToFreq.getOrDefault(result, 0) + 1);
                    sizeToFreq.notify();
                }
            });
            futures.add(future);
        }
        for (Future<?> future : futures) {
            future.get();
        }
        thread.interrupt();
        thread.join();
        executer.shutdown();

        List<Map.Entry<Integer, Integer>> list = sizeToFreq.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .toList();

        System.out.println("---");
        System.out.printf("Самое частое количество повторений %d (встретилось %d раз)%n", list.getFirst().getKey(), list.getFirst().getValue());
        System.out.println("Другие размеры: ");
        for (int i = 1; i < list.size(); i++) {
            System.out.printf("- %d (%d раз)%n", list.get(i).getKey(), list.get(i).getValue());
        }

    }

    public static int calculateRepeatOfR(String string, char ch) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }


    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
