package dk.lbi.adventofcode.day4;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class day4 {
    public static void main(String[] args) throws IOException {
        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day4input.txt").toPath());

    }
}
