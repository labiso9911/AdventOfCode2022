package dk.lbi.adventofcode.day7;

public class FileSystemItem {
    enum nodeType {DIRECTORY, FILE}
    int id;
    String name;
    FileSystemItem.nodeType type;
    int size;

    public FileSystemItem(int id, String name, nodeType type, int size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public nodeType getType() {
        return type;
    }

    public void setType(nodeType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
