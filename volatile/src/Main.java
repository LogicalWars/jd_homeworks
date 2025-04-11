import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final int THREAD_COUNT = 3;
    static AtomicInteger beautifulWordsCount3 = new AtomicInteger(0);
    static AtomicInteger beautifulWordsCount4 = new AtomicInteger(0);
    static AtomicInteger beautifulWordsCount5 = new AtomicInteger(0);


    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT)) {
            executorService.submit(() -> {
                for (String text : texts) {
                    switch (text.length()) {
                        case 3:
                            if (isPalindrome(text)) beautifulWordsCount3.incrementAndGet();
                            break;
                        case 4:
                            if (isPalindrome(text)) beautifulWordsCount4.incrementAndGet();
                            break;
                        case 5:
                            if (isPalindrome(text)) beautifulWordsCount5.incrementAndGet();
                    }
                }
            });

            executorService.submit(() -> {
                for (String text : texts) {
                    switch (text.length()) {
                        case 3:
                            if (isSingleLetterString(text)) beautifulWordsCount3.incrementAndGet();
                            break;
                        case 4:
                            if (isSingleLetterString(text)) beautifulWordsCount4.incrementAndGet();
                            break;
                        case 5:
                            if (isSingleLetterString(text)) beautifulWordsCount5.incrementAndGet();
                    }
                }
            });
            executorService.submit(() -> {
                for (String text : texts) {
                    switch (text.length()) {
                        case 3:
                            if (isLettersInAscendingOrder(text)) beautifulWordsCount3.incrementAndGet();
                            break;
                        case 4:
                            if (isLettersInAscendingOrder(text)) beautifulWordsCount4.incrementAndGet();
                            break;
                        case 5:
                            if (isLettersInAscendingOrder(text)) beautifulWordsCount5.incrementAndGet();
                    }
                }
            });
        }
        System.out.printf("Красивых слов с длиной 3: %d шт%n", beautifulWordsCount3.get());
        System.out.printf("Красивых слов с длиной 4: %d шт%n", beautifulWordsCount4.get());
        System.out.printf("Красивых слов с длиной 5: %d шт%n", beautifulWordsCount5.get());
    }

    public static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public static boolean isSingleLetterString(String str) {
        boolean result = true;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                result = false;
            }
        }
        return result;
    }

    public static boolean isLettersInAscendingOrder(String str) {
        boolean result = true;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) < str.charAt(i - 1)) {
                result = false;
            }
        }
        return result;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


}