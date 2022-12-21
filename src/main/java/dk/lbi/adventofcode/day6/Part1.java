package dk.lbi.adventofcode.day6;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Part1 {
    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day6input.txt").toPath());

        Queue<String> stream = new LinkedList<>();

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                addToLimitedQueue(stream, 14, line.substring(i,i+1));
                if(isUnique(stream)) {
                    System.out.println(i+1);
                    return;
                }
            }
        }
    }
    public Queue<String> addToLimitedQueue(Queue<String> characters, Integer limit, String characterToAdd){
        if (characters.size() >= limit){
            characters.remove();
            characters.add(characterToAdd);
        } else {
            characters.add(characterToAdd);
        }
        return characters;
    }
    public boolean isUnique(Queue<String> input) {
        Set<String> set = new HashSet<>(input);
        if (set.size() == input.size() && input.size() >= 14) return true;
        return false;
    }
}