package dk.lbi.adventofcode.day12;

import dk.lbi.adventofcode.utils.GetResourceFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Part1 {

    public void doWork() throws IOException {
        System.out.println("Hello");

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day9input.txt").toPath());

        char[][] map = new char[6][8];
        // Input all field into 2d array
        for (int i = 0; i < lines.size(); i++) {
            for (int y = 0; y < lines.get(i).length(); y++) {
                map[i][y] = lines.get(i).charAt(y);
            }
        }
        System.out.println("Yeehaw");



    }

    private int getDistance(char currentNode, char adjecentNode) {
        // If the elevation is the same, set distance to 1
        if (adjecentNode-currentNode == 0) return 1;
        // If the elevation is one, set distance to 2
        if (adjecentNode-currentNode == 1) return 2;
        // if the elevation is minus one, set distance to 0
        if (adjecentNode-currentNode == -1) return 0;
    }
}
