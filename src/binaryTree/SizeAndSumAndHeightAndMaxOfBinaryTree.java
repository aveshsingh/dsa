package binaryTree;

import java.util.Stack;

public class SizeAndSumAndHeightAndMaxOfBinaryTree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node data;
        int state;

        public Pair(Node data, int state) {
            this.data = data;
            this.state = state;
        }
    }

    public static void display(Node node) {
        // pre order display binary tree
        if (node == null) {
            return;
        }
        String str = "";
        str += node.left == null ? "." : node.left.data;
        str += "->" + node.data + "<-";
        str += node.right == null ? "." : node.right.data;
        System.out.print(str);
        System.out.println();
        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if (node == null) {
            return 0;
        }
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        int size = leftSize + rightSize + 1;
        return size;
    }

    public static int height(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int maxHeight = Math.max(leftHeight, rightHeight) + 1;
        return maxHeight;
    }


    public static int max(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int leftMax = max(node.left);
        int rightMax = max(node.right);
        int max = Math.max(node.data, Math.max(leftMax, rightMax));
        return max;
    }

    public static int sum(Node node) {
        if (node == null) {
            return 0;
        }
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);
        int sum = leftSum + rightSum + node.data;
        return sum;
    }


    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Stack<Pair> stack = new Stack<>();
        Node root = new Node(arr[0], null, null);
        stack.push(new Pair(root, 1));
        int index = 0;
        while (stack.size() > 0) {
            Pair currentPair = stack.peek();
            if (currentPair.state == 1) {
                index++;
                if (arr[index] != null) {
                    Node node = new Node(arr[index], null, null);
                    currentPair.data.left = node;
                    stack.push(new Pair(node, 1));
                } else {
                    currentPair.data.left = null;
                }
                currentPair.state++;
            } else if (currentPair.state == 2) {
                index++;
                if (arr[index] != null) {
                    Node node = new Node(arr[index], null, null);
                    currentPair.data.right = node;
                    stack.push(new Pair(node, 1));
                } else {
                    currentPair.data.right = null;
                }
                currentPair.state++;
            } else {
                stack.pop();
            }
        }
        display(root);
        System.out.println("max of tree is::::::" + max(root));
        System.out.println("size of tree is::::::" + size(root));
        System.out.println("heoght of tree is::::::" + height(root));
        System.out.println("sum of tree is::::::" + sum(root));
    }
}
