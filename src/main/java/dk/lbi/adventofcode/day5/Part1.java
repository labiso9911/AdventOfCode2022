package dk.lbi.adventofcode.day5;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Part1 {
    public void doWork() throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day5input.txt").toPath());



        Stack<String> stack1 = new Stack<>();

        System.out.println(stack1.pop());
        System.out.println("hej");
    }
}
