import java.io.File;
import java.util.List;

public class FileSearch {
     public  void searchFile(File rootFile, List<File> filesCsv, List<File> filesJson) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFile(file, filesCsv, filesJson);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".csv")) {
                            filesCsv.add(file);
                        } else {
                            file.getName().toLowerCase().endsWith(".json");
                            filesJson.add(file);
                        }
                    }
                }
            }
        }

    }

}
