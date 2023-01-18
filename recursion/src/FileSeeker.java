import java.io.File;
import java.util.*;

public class FileSeeker {

    public static List<File> getAllFiles(File directory) {
        if (directory == null || directory.listFiles() == null)
            return new ArrayList<>();

        List<File> files = new ArrayList<>();
        for (File item : directory.listFiles()) {
            if (item.isFile()) {
                files.add(item);
            } else {
                files.addAll(getAllFiles(item));
            }
        }

        return files;
    }
}
