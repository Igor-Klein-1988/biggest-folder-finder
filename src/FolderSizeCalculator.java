import java.io.File;
import java.util.LinkedList;
import java.util.List;
//import java.util.concurrent.RecursiveTask;
import java.util.*;
import java.util.concurrent.*;

public class FolderSizeCalculator extends RecursiveTask {
    private Node node;

    public FolderSizeCalculator(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        File folder = node.getFolder();
        if (folder.isFile()) {
            long length = folder.length();
            node.setSize(length);
            return length;
        };

        long sum = 0;
        File[] files = folder.listFiles();
        List<FolderSizeCalculator> subTask = new LinkedList<>();

        for (File file : files) {
            Node child = new Node(file, node.getLimit());
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork(); // ! запуск асинхронизации
            subTask.add(task);
            node.addChild(child);
        }

        for (FolderSizeCalculator task : subTask) {
            sum += (Long) task.join(); // ! дождемся выполнения задачи и прибавим результат
        }

        node.setSize(sum);
        return sum;
    }
}
