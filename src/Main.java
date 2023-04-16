import java.io.File;
import java.util.HashMap;
//import java.util.concurrent.ForkJoinPool;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i <args.length; i++) {
            System.out.println(i + " => " + args[i]);
        }
//        System.exit(0);

//        users/igorklein/Downloads  IdeaProjects/BiggestFolderFinder
        String[] argums = {"-d", "/users/igorklein/Downloads", "-l", "100M"};
//        System.out.println("rrr");
        ParametersBag bag = new ParametersBag(argums);
//        System.out.println("4444");

//        String folderPath = "/users/igorklein/Downloads";


//        String folderPath = "/users/igorklein/IdeaProjects";

        String folderPath = bag.getPath();

//        long sizeLimit = 50 * 1024 * 1024;
        long sizeLimit = bag.getLimit();
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);
        System.out.println("444-55");

//        System.out.println(System.getProperties().get("user.dir")); // ! BiggestFolderFinder
//
//        System.out.println("getFolderSize = " + getFolderSize(file)); // ! 5_944_955 это байты

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        long size = (long) pool.invoke(calculator);
        System.out.println("root = " + root);

        System.out.println("root.getSize() = " + root.getSize());
        System.out.println("size long 1 var" + size);
//        System.out.println("getSizeFromHumanReadable(\"235K\") = " + getSizeFromHumanReadable("235K")); // ! 240640
//        System.out.println("getHumanReadableSize(240640) = " + getHumanReadableSize(240640)); // ! 240640
    }
}