package dk.lbi.adventofcode.day7;

import dk.lbi.adventofcode.utils.GetResourceFile;
import dk.lbi.adventofcode.utils.TreeNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class Part1 {

    public void doWork() throws IOException {

        GetResourceFile resourceFile = new GetResourceFile();
        List<String> lines = Files.readAllLines(resourceFile.getFile("day7input.txt").toPath());

        List<Integer> directorySizes = new ArrayList<>();
        int idCounter = 0;

        TreeNode<FileSystemItem> node = new TreeNode<>(new FileSystemItem(++idCounter, "/", FileSystemItem.nodeType.DIRECTORY, 0));
        TreeNode<FileSystemItem> currentNode = node;
        for (String line : lines.subList(1, lines.size())) {

            if(line.startsWith("$ cd")) {
                // Going one level up
                if (line.substring(5).equals("..")) {
                    currentNode = currentNode.parent;
                // Changing directory
                } else {
                    for (TreeNode<FileSystemItem> n: currentNode.children) {
                        if (n.data.getName().equals(line.substring(5))){
                            currentNode = n;
                        }
                    }
                }
            }
            // Creating new directories
            if(line.startsWith("dir")) {
                currentNode.addChild(new FileSystemItem(
                        ++idCounter,
                        line.substring(4),
                        FileSystemItem.nodeType.DIRECTORY,
                        0
                        ));
            }
            // Creating new files - If the first charcater is a number its a file
            if(line.substring(0,1).matches("[0-9]+")) {
                String[] file = line.split(" ");
                currentNode.addChild(new FileSystemItem(
                        ++idCounter,
                        file[1],
                        FileSystemItem.nodeType.FILE,
                        Integer.parseInt(file[0])
                ));
                // Add size to parent
                currentNode.data.setSize(currentNode.data.getSize() + Integer.parseInt(file[0]));
            }

        }
        // Summarize directory size
        summarizeDirectorySize(node);
        getSummarizedSize(node);

        // Get list of sizes
        getDirectorySize(node, directorySizes);
        Collections.sort(directorySizes);

        Integer directoriesUnder100000Sum = directorySizes.stream()
                        .filter(value -> value < 100000)
                .reduce(0, Integer::sum);

        Integer spaceNeeded = 10822529;

        Integer deletedSpaceNeeded = directorySizes.stream()
                .filter(i -> i > spaceNeeded).findFirst().
                orElseThrow(() -> new NoSuchElementException("No value"));
        System.out.println("Directories under 100000 " + directoriesUnder100000Sum);
        System.out.println("Delete directory: " + deletedSpaceNeeded);
    }

    public void summarizeDirectorySize(TreeNode<FileSystemItem> current) {
        if (current == null) {
            return;
        }
        for (TreeNode<FileSystemItem> child : current.children) {
            if (child.data.getType().equals(FileSystemItem.nodeType.DIRECTORY)) {
                summarizeDirectorySize(child);
                current.data.setSize(current.data.getSize() + child.data.getSize());
            }
        }
    }

    public void getSummarizedSize(TreeNode<FileSystemItem> current) {
        int result = 0;
        // Check if root
        if (current.parent == null) {
            System.out.println(current.data.getName() + ";" + current.data.getType() + ";" + current.data.getSize());
        }

        for (TreeNode<FileSystemItem> child : current.children) {
            if (child.data.getType().equals(FileSystemItem.nodeType.DIRECTORY) &&
                child.data.getSize() <= 100000 && child.data.getSize() > 0) {
                result += child.data.getSize();
                System.out.println(child.data.getSize());
            }
            getSummarizedSize(child);
        }
    }

    public void getDirectorySize(TreeNode<FileSystemItem> current, List<Integer> list) {
        // Check if root
        if (current.parent == null) {
            System.out.println(current.data.getName() + ";" + current.data.getType() + ";" + current.data.getSize());
        }

        for (TreeNode<FileSystemItem> child : current.children) {
            if (child.data.getType().equals(FileSystemItem.nodeType.DIRECTORY)) {
                list.add(child.data.getSize());
            }
            getDirectorySize(child, list);
        }

    }
}

//free space: 19177471
//
//Needed space: 10.822.529