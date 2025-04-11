import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    static final int TEXT_LENGTH = 100_000;
    static final int COUNT_TEXT = 10_000;
    static final String CHARS_TEXT = "abc";
    static final int QUEUE_SIZE = 100;

    static BlockingQueue<String> queueOfA = new ArrayBlockingQueue<>(QUEUE_SIZE);
    static BlockingQueue<String> queueOfB = new ArrayBlockingQueue<>(QUEUE_SIZE);
    static BlockingQueue<String> queueOfC = new ArrayBlockingQueue<>(QUEUE_SIZE);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();
        futures.add(executorService.submit(() -> calculateMaxOfChar(queueOfA, 'a')));
        futures.add(executorService.submit(() -> calculateMaxOfChar(queueOfB, 'b')));
        futures.add(executorService.submit(() -> calculateMaxOfChar(queueOfC, 'c')));


        for (int i = 0; i < COUNT_TEXT; i++) {
            String s = generateText(CHARS_TEXT, TEXT_LENGTH);
            try {
                queueOfA.put(s);
                queueOfB.put(s);
                queueOfC.put(s);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executorService.shutdown();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static int countChars(String text, char ch) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static void calculateMaxOfChar(BlockingQueue<String> queue, char ch) {
        String result = null;
        int max = 0;
        int countOfChar;
        try {
            while (true) {
                String str = queue.poll(2, TimeUnit.SECONDS);
                if (str != null) {
                    countOfChar = countChars(str, ch);
                    if (countOfChar > max) {
                        result = str;
                        max = countOfChar;
                    }
                } else {
                    System.out.printf("Максимальное кол-во символов '%s': %d\n%s\n---%n", ch, max, result);
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}