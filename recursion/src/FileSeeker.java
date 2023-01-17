import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSeeker {

    public static List<File> getAllFiles(File directory) {
        return getAllFiles(
                new ArrayList<>(),
                directory
        );
    }

    private static List<File> getAllFiles(List<File> foundedFiles, File currentFile) {
        File[] listFiles = currentFile.listFiles();
        if (listFiles == null)
            return foundedFiles;
        for (File item : listFiles) {
            foundedFiles.add(item);
            if (item.isDirectory()) {
                getAllFiles(foundedFiles, item);
            }
        }

        return foundedFiles;
    }

}
