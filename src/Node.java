import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private File folder;
    public ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;


    public Node(File folder, long limit) {
        this(folder);
        this.limit = limit;
    }

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    private long setLimit(long limit) {
        return limit;
    }

    public File getFolder() {
        return folder;
    }

    public long getLimit() {
        return limit;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        // ! StringBuffer потоко безопасен,
        // ! но у нас один поток поэтомуможно StringBuilder
        StringBuilder builder = new StringBuilder();

        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + "(" + level + ")" + " - " + size + "\n");
        for (Node child : children) {
            if (child.getSize() < limit) {
                child.toString();
                continue;
            }
//            String levelSpace = "";
//            for (int i = 0; i < level; i++){
//                levelSpace = levelSpace + " ";
//            }
            System.out.println(child.getSize() < limit);

            builder.append(" ".repeat(level + 1) + " - limit = " + limit + "     " + child.toString());
        }

        return builder.toString();
    }
}
