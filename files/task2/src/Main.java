import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        final String PATH = "D:\\Projects\\jd_homeworks\\files\\task1\\Games\\savegames";
        List<GameProgress> games = List.of(
                new GameProgress(20, 30, 25, 45.5),
                new GameProgress(30, 20, 27, 55.3),
                new GameProgress(40, 26, 30, 60.0));
        for (GameProgress game : games) {
            saveGame(PATH, game);
        }
        List<String> pathFiles = new ArrayList<>();
        for (File filePath: new File(PATH).listFiles()) {
            pathFiles.add(filePath.getPath());
        }
        zipFiles(PATH, pathFiles);
    }

    public static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(new File(path, game.hashCode() + ".dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZip, List<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip+"\\saveGames.zip"))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(new File(file))) {
                    ZipEntry entry = new ZipEntry(new File(file).getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        for (File filePath: new File(pathZip).listFiles()) {
            for (String file : files) {
                if (filePath.getName().equals(new File(file).getName()) && filePath.getName().endsWith(".dat")) {
                    filePath.delete();
                }
            }
        }
    }
}