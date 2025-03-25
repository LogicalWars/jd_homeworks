import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final String PATH = "D:\\Projects\\jd_homeworks\\files\\task1\\Games";

    public static void main(String[] args) {

        createDir(new File(PATH), List.of("src", "res", "savegames"));
        createDir(new File(PATH + "\\src"), List.of("main", "test"));
        createDir(new File(PATH + "\\res"), List.of("drawables", "vectors", "icons"));
        createFile(new File(PATH + "\\src\\main"), List.of("Main.java", "Utils.java"));
    }

    public static void createDir(File parentDir, List<String> dirs) {
        for (String dir : dirs) {
            if (new File(parentDir, dir).exists()) {
                writeLog(LocalTime.now() + " [WARNING] " + "Директория \"" + dir + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
            } else {
                if (new File(parentDir, dir).mkdirs()) {
                    writeLog(LocalTime.now() + " [INFO] " + "Директория \"" + dir + "\" создана по пути: " + parentDir.getAbsolutePath() + "\n");
                } else {
                    writeLog(LocalTime.now() + " [ERROR] " + "Директория \"" + dir + "\" не создана по пути: " + parentDir.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void createFile(File parentDir, List<String> files) {
        for (String file : files) {

            if (new File(parentDir, file).exists()) {
                writeLog(LocalTime.now() + " [WARNING] " + "Файл \"" + file + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
            } else {
                try {
                    if (new File(parentDir, file).createNewFile()) {
                        writeLog(LocalTime.now() + " [INFO] " + "Файл \"" + file + "\" создан по пути: " + parentDir.getAbsolutePath() + "\n");
                    } else {
                        writeLog(LocalTime.now() + " [ERROR] " + "Файл \"" + file + "\" не создан по пути: " + parentDir.getAbsolutePath() + "\n");
                    }
                } catch (IOException e) {
                    writeLog(LocalTime.now() + " [ERROR] " + "Файл \"" + file + " Не создан. " + e + "\n");
                }
            }
        }
    }

    public static void writeLog(String logs) {
        if (!new File(PATH, "temp").exists()) {
            new File(PATH, "temp").mkdirs();
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(PATH + "\\temp", "temp.txt"), true))) {
                bw.write(logs);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}