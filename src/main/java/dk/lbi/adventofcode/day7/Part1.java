package dk.lbi.adventofcode.day7;

import dk.lbi.adventofcode.utils.TreeNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {

    public String doWork() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/lasbas/Documents/Java-projects/AdventOfCode/src/main/resources/day7input.txt"));

        int idCounter = 0;

        TreeNode<FileSystemItem> node = new TreeNode<>(new FileSystemItem(++idCounter, "/", FileSystemItem.nodeType.DIRECTORY, 0));
        TreeNode<FileSystemItem> currentNode = node;
        for (String line : lines.subList(1, lines.size())) {

            if(line.substring(0, 4).equals("$ cd")) {
                // Going one level up
                if (line.substring(4).equals("..")) {
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
            if(line.substring(0, 3).equals("dir")) {
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
                        Integer.valueOf(file[0])
                ));
                // Add size to parent
                currentNode.data.setSize(currentNode.data.getSize() + Integer.valueOf(file[0]));
            }

        }
        // Summarize directory size
        summarizeDirectorySize(node);
        getSummarizedSize(node);

        return "Blah";
    }

    public void summarizeDirectorySize(TreeNode<FileSystemItem> current) {
        for (TreeNode<FileSystemItem> child : current.children) {
            if (child.data.getType().equals(FileSystemItem.nodeType.DIRECTORY)) {
                current.data.setSize(current.data.getSize() + child.data.getSize());
            }
            summarizeDirectorySize(child);
        }
    }

    public void getSummarizedSize(TreeNode<FileSystemItem> current) {
        int result = 0;
        for (TreeNode<FileSystemItem> child : current.children) {
            System.out.println(child.data.getName() + ";" + child.data.getType() + ";" + child.data.getSize());
            if (child.data.getType().equals(FileSystemItem.nodeType.DIRECTORY) &&
                    child.data.getSize() < 100000 && child.data.getSize() > 1) {
                result += child.data.getSize();
            }
            getSummarizedSize(child);
        }
        //System.out.println(result);
    }
}
