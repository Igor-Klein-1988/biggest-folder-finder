import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask {
    private File folder;

    public FolderSizeCalculator(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() {
        if (folder.isFile()) return folder.length();

        long sum = 0;
        File[] files = folder.listFiles();
        List<FolderSizeCalculator> subTask = new LinkedList<>();

        for (File file : files) {
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // ! запуск асинхронизации
            subTask.add(task);
        }

        for (FolderSizeCalculator task : subTask) {
            sum += (Long) task.join(); // дождемся выполнения задачи и прибавим результат
        }

        return sum;
    }
}
