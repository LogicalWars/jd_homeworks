import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String PATH = "D:\\Projects\\jd_homeworks\\files\\task1\\Games";
        StringBuilder log = new StringBuilder();
        List<String> dirs1 = List.of("src", "res", "savegames", "temp");
        List<String> dirs2 = List.of("main", "test");
        List<String> dirs3 = List.of("drawables", "vectors", "icons");
        List<String> files1 = List.of("Main.java", "Utils.java");
        if (createDir(new File(PATH), dirs1, log) &&
                createDir(new File(PATH + "\\src"), dirs2, log) &&
                createFile(new File(PATH+"\\src\\main"), files1, log) &&
                createDir(new File(PATH + "\\res"), dirs3, log)) {
            log.append(LocalTime.now() + " [INFO] " + "Папки и файлы успешно созданы");
        } else {
            log.append(LocalTime.now() + " [ERROR] " + "Папки и файлы не созданы");
        }
        try (BufferedWriter bw = new BufferedWriter (new FileWriter(new File(PATH + "\\temp", "temp.txt")))) {
            bw.write(log.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createDir(File parentDir, List<String> dirs, StringBuilder log) {
        boolean result = true;
        for (String dir : dirs) {
            if (new File(parentDir, dir).exists()) {
                log.append(LocalTime.now() + " [ERROR] " + "Директория \"" + dir + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
            } else {
                if (new File(parentDir, dir).mkdirs()) {
                    log.append(LocalTime.now() + " [INFO] " + "Директория \"" + dir + "\" создана по пути: " + parentDir.getAbsolutePath() + "\n");
                } else {
                    log.append(LocalTime.now() + " [ERROR] " + "Директория \"" + dir + "\" не создана по пути: " + parentDir.getAbsolutePath() + "\n");
                    result = false;
                }
            }
        }
        return result;
    }

    public static boolean createFile(File parentDir, List<String> files, StringBuilder log) {
        boolean result = true;
        for (String file : files) {
            try {
                if (new File(parentDir, file).exists()) {
                    log.append(LocalTime.now() + " [ERROR] " + "Файл \"" + file + "\" уже существует по пути: " + parentDir.getAbsolutePath() + "\n");
                } else {
                    if (new File(parentDir, file).createNewFile()) {
                        log.append(LocalTime.now() + " [INFO] " + "Файл \"" + file + "\" создан по пути: " + parentDir.getAbsolutePath() + "\n");
                    } else {
                        log.append(LocalTime.now() + " [ERROR] " + "Файл \"" + file + "\" не создан по пути: " + parentDir.getAbsolutePath() + "\n");
                        result = false;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}