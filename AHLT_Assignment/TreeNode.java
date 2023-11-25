//package BinaryTrees;

public class TreeNode {
    private String item;
    private TreeNode leftChild;
    private TreeNode middleChild;
    private TreeNode rightChild;

    // Constructor
    public TreeNode(String newItem) {
        item = newItem;
        leftChild = null;
        middleChild = null;
        rightChild = null;
    }

    public TreeNode(String newItem,
            TreeNode left, TreeNode middle, TreeNode right) {
        item = newItem;
        middleChild = middle;
        leftChild = left;
        rightChild = right;
    }

    public TreeNode(String newItem, TreeNode middle) {
        item = newItem;
        middleChild = middle;
    }

    public TreeNode(String newItem, TreeNode left, TreeNode right) {
        item = newItem;
        middleChild = null;
        leftChild = left;
        rightChild = right;
    }

    // Returns the item field.
    public String getItem() {
        return item;
    }

    // Sets the item field to the new value newItem.
    public void setItem(String newItem) {
        item = newItem;
    }

    // Returns the reference to the left child.
    public TreeNode getLeft() {
        return leftChild;
    }

    // Sets the left child reference to left.
    public void setLeft(TreeNode left) {
        leftChild = left;
    }

    // Returns the reference to the middle child.
    public TreeNode getMiddle() {
        return middleChild;
    }

    // Sets the middle child reference to middle.
    public void setMiddle(TreeNode middle) {
        middleChild = middle;
    }

    // Returns the reference to the right child.
    public TreeNode getRight() {
        return rightChild;
    }

    // Sets the right child reference to right.
    public void setRight(TreeNode right) {
        rightChild = right;
    }

}