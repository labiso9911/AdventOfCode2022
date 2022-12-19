package dk.lbi.adventofcode.day4;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part1 {

    int part1Result = 0;
    int part2Result = 0;
    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day4input.txt").toPath());

        for (String line : lines) {
            String[] splitLine = line.split(",");
            String[] range1Line = splitLine[0].split("-");
            String[] range2Line = splitLine[1].split("-");

            List<Integer> result1 = new ArrayList<>();
            List<Integer> result2 = new ArrayList<>();

            List<Integer> range1 = IntStream
                    .rangeClosed(Integer.parseInt(range1Line[0]), Integer.parseInt(range1Line[1]))
                    .boxed()
                    .collect(Collectors.toList());
            List<Integer> range2 = IntStream
                    .rangeClosed(Integer.parseInt(range2Line[0]), Integer.parseInt(range2Line[1]))
                    .boxed()
                    .collect(Collectors.toList());




            for (Integer i: range1) {
                if (range2.contains(i)){
                    result1.add(i);
                }
            }
            for (Integer i: range2) {
                if (range1.contains(i)){
                    result2.add(i);
                }
            }

            if (result1.size() == range1.size() || result2.size() == range2.size()){
                part1Result++;
            }

            if(!Collections.disjoint(range1, range2)){
                part2Result++;
            }

            System.out.println("Result Part 1: " + part1Result);
            System.out.println("Result Part 2: " + part2Result);



            System.out.println("Hello");
        }
    }
}
