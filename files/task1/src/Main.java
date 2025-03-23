import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String PATH = "D:\\Projects\\jd_homeworks\\files\\task1\\Games";
        boolean resultCreate = true;
        StringBuilder log = new StringBuilder();
        new File(PATH, "temp").mkdirs();

        List<String> dirs1 = List.of("src", "res", "savegames");
        List<String> dirs2 = List.of("main", "test");
        List<String> dirs3 = List.of("drawables", "vectors", "icons");
        List<String> files1 = List.of("Main.java", "Utils.java");

        resultCreate &= createDir(new File(PATH), dirs1, PATH);
        resultCreate &= createDir(new File(PATH + "\\src"), dirs2, PATH);
        resultCreate &= createFile(new File(PATH + "\\src\\main"), files1, PATH);
        resultCreate &= createDir(new File(PATH + "\\res"), dirs3, PATH);

        if (resultCreate) {
            log.append(LocalTime.now() + " [INFO] " + "Папки и файлы успешно созданы");
        } else {
            log.append(LocalTime.now() + " [ERROR] " + "Папки и файлы не созданы");
        }

        writeLog(PATH, "temp.txt", log.toString());
    }

    public static boolean createDir(File parentDir, List<String> dirs, String pathToLog) {
        boolean result = true;
        StringBuilder log = new StringBuilder();
        for (String dir : dirs) {
            if (new File(parentDir, dir).exists()) {
                writeLog(pathToLog, "temp.txt", LocalTime.now()
                        + " [ERROR] " + "Директория \"" + dir + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
            } else {
                if (new File(parentDir, dir).mkdirs()) {
                    writeLog(pathToLog, "temp.txt", LocalTime.now()
                            + " [INFO] " + "Директория \"" + dir + "\" создана по пути: " + parentDir.getAbsolutePath() + "\n");
                } else {
                    writeLog(pathToLog, "temp.txt", LocalTime.now()
                            + " [ERROR] " + "Директория \"" + dir + "\" не создана по пути: " + parentDir.getAbsolutePath() + "\n");
                    result = false;
                }
            }
        }
        return result;
    }

    public static boolean createFile(File parentDir, List<String> files, String pathToLog) {
        boolean result = true;
        StringBuilder log = new StringBuilder();
        for (String file : files) {
            try {
                if (new File(parentDir, file).exists()) {
                    writeLog(pathToLog, "temp.txt", LocalTime.now()
                            + " [ERROR] " + "Файл \"" + file + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
                } else {
                    if (new File(parentDir, file).createNewFile()) {
                        writeLog(pathToLog, "temp.txt", LocalTime.now()
                                + " [INFO] " + "Файл \"" + file + "\" создан по пути: " + parentDir.getAbsolutePath() + "\n");
                    } else {
                        writeLog(pathToLog, "temp.txt", LocalTime.now()
                                + " [ERROR] " + "Файл \"" + file + "\" не создан по пути: " + parentDir.getAbsolutePath() + "\n");
                        result = false;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static void writeLog(String path, String filename, String logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + "\\temp", filename), true))) {
            bw.write(logs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}