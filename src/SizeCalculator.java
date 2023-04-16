import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2multiplier = getMultiplier();

    private static HashMap<Character, Integer> getMultiplier() {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(multipliers[i], (int) Math.pow(1024, i));
        }

        return char2multiplier;
    }

    // ! больше этот метод не нужен
    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }

//        sum = Stream.of(files).mapToLong(File::length).sum();
        return sum;
    }

    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = (double) size / Math.pow(1024, i);
            if (value < 1024) return Math.round(value * 100) / 100. + "" + sizeMultipliers[i] +
                    (i > 0 ? "b" : "");

        }
        return "Very big";
    }

    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = size.replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        System.out.println("sizeFactor: " + sizeFactor);
        int multiplier = char2multiplier.get(sizeFactor);
        System.out.println("multiplier: " + multiplier);

        long length = multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", ""));
        return length;
    }
}
