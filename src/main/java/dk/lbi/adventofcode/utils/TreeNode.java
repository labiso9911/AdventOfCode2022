package dk.lbi.adventofcode.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T>{
    public T data;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;

    private List<TreeNode<T>> elementsIndex;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }
}
