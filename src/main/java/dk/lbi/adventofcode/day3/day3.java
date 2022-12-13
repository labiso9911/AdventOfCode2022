package dk.lbi.adventofcode.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class day3 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/lasbas/Documents/Java-projects/AdventOfCode/src/main/resources/day3input.txt"));
        HashSet<Character> firstHalf = new HashSet<>();
        HashSet<Character> fullSet = new HashSet<>();
        HashMap<Character, Integer> batchMap = new HashMap<>();

        char priorityItem = 'a';
        int priority = 0;

        int batchPriority = 0;

        for (int i = 0; i < lines.size(); i++) {

            // Part 2
            if (i % 3 == 0 && i > 0) {
                batchMap.clear();
            }

            String line = lines.get(i);
            for (int y = 0; y < line.length(); y++) {
                //Control first half
                if (y < line.length() / 2) {
                    firstHalf.add(line.charAt(y));
                    fullSet.add(line.charAt(y));
                    // Control second half
                } else {
                    if (firstHalf.contains(line.charAt(y))) {
                        priorityItem = line.charAt(y);
                    }
                    fullSet.add(line.charAt(y));
                }
            }

            // Part 2
            for (Character c : fullSet){
                batchMap.merge(c, 1, Integer::sum);
            }

            for (Map.Entry<Character, Integer> entry: batchMap.entrySet()) {
                if (entry.getValue() == 3) {
                    batchPriority += getPriority(entry.getKey());
                }
            }

            priority += getPriority(priorityItem);

            firstHalf.clear();
            fullSet.clear();
        }
        System.out.println("Priority is : " + priority);
        System.out.println("Batch-Priority is : " + batchPriority);
    }

    private static int getPriority(char priorityItem) {
        if (Character.isUpperCase(priorityItem)) {
            return Character.toLowerCase(priorityItem) - 'a' + 27;
        } else {
            return Character.toLowerCase(priorityItem) - 'a' + 1;
        }
    }
}
