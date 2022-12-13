package dk.lbi.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class day2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/lasbas/Documents/Java-projects/AdventOfCode/src/main/resources/day2input.txt"));

        int part1_score = 0;
        int part2_score = 0;
        for (String line : lines) {
            String[] s = line.split(" ");
            part1_score += getRoundScorePart1(s[0], s[1]);
            part2_score += getRoundScorePart2(s[0], s[1]);
        }

        System.out.println("Part 1 Score is: " + part1_score);
        System.out.println("Part 2 Score is: " + part2_score);
    }

    // A = Rock = 1 point
    // B = paper = 2 point
    // C = Scissor = 3 point

    // X = lose
    // Y = draw
    // Z = win

    // Win = 6 Points
    // Draw = 3 points
    // Loss = 0 points
    public static int getRoundScorePart2(String opponentChoise, String mychoice) {
        switch (mychoice) {
            //Rock
            case "X" -> {
                if (Objects.equals(opponentChoise, "C")) return 2 + 0; // Loss
                if (Objects.equals(opponentChoise, "A")) return 3 + 0; // Loss
                if (Objects.equals(opponentChoise, "B")) return 1 + 0; // Loss
            }
            case "Y" -> {
                if (Objects.equals(opponentChoise, "A")) return 1 + 3; // Draw
                if (Objects.equals(opponentChoise, "B")) return 2 + 3; // Draw
                if (Objects.equals(opponentChoise, "C")) return 3 + 3; // Draw
            }
            case "Z" -> {
                if (Objects.equals(opponentChoise, "B")) return 3 + 6; // Win
                if (Objects.equals(opponentChoise, "C")) return 1 + 6; // Win
                if (Objects.equals(opponentChoise, "A")) return 2 + 6; // Win
            }
        }
        return 0;
    }


    // A = Rock
    // B = paper
    // C = Scissor

    // X = Rock = 1 point
    // Y = Paper = 2 point
    // Z = Scissor = 3 point

    // Win = 6 Points
    // Draw = 3 points
    // Loss = 0 points
    public static int getRoundScorePart1(String opponentChoise, String mychoice) {
        switch (mychoice) {
            //Rock
            case "X" -> {
                if (Objects.equals(opponentChoise, "C")) return 1 + 6; // Win
                if (Objects.equals(opponentChoise, "A")) return 1 + 3; // Draw
                if (Objects.equals(opponentChoise, "B")) return 1 + 0; // Loss
            }
            case "Y" -> {
                if (Objects.equals(opponentChoise, "A")) return 2 + 6; // Win
                if (Objects.equals(opponentChoise, "B")) return 2 + 3; // Draw
                if (Objects.equals(opponentChoise, "C")) return 2 + 0; // Loss
            }
            case "Z" -> {
                if (Objects.equals(opponentChoise, "B")) return 3 + 6; // Win
                if (Objects.equals(opponentChoise, "C")) return 3 + 3; // Draw
                if (Objects.equals(opponentChoise, "A")) return 3 + 0; // Loss
            }
        }
        return 0;
    }
}
