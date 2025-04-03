import java.time.LocalDateTime;

public class Logger {
    protected int num = 1;
    private static Logger logger;

    private Logger() {}

    public void log(String msg) {
        System.out.println("["+ LocalDateTime.now() +" "+ num++ + "] " + msg);
    }

    public static Logger getInstance() {
        return logger != null ? logger : new Logger();
    }
}