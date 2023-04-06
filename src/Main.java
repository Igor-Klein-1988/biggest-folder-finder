import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String folderPath = "/users/igorklein/IdeaProjects";
        File file = new File(folderPath);
        System.out.println(file.length());

        System.out.println(System.getProperties().get("user.dir")); // ! BiggestFolderFinder

        System.out.println("getFolderSize = " + getFolderSize(file)); // ! 5944955 это байты
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}