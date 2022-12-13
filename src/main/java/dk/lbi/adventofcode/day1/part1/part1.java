package dk.lbi.adventofcode.day1.part1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/lasbas/Documents/Java-projects/AdventOfCode/src/main/resources/day2input.txt"));


        List<Integer> elves = new ArrayList<>();
        int calories = 0;

        for (String line : lines) {
            if (!line.isEmpty()) {
                calories += Integer.parseInt(line);
            }
            if (line.isEmpty())  {
                elves.add(calories);
                calories = 0;
            }
        }
        elves.sort(Collections.reverseOrder());

        System.out.println("Max " + elves.get(0));
        int topthree = elves.get(0) + elves.get(1) + elves.get(2);
        System.out.println("Top 3 Sum: " + topthree );



    }

}
