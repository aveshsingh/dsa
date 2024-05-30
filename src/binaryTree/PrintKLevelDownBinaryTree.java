package binaryTree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class PrintKLevelDownBinaryTree {

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

    public static void kLevelDownPrint(Node node, int k) {
        if (node == null || k < 0) {
            return;
        }
        if (k == 0) {
            System.out.println(node.data);
        }
        kLevelDownPrint(node.left, k - 1);
        kLevelDownPrint(node.right, k - 1);
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
        kLevelDownPrint(root,2);
    }
}
