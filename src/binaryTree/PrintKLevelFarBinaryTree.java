package binaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class PrintKLevelFarBinaryTree {

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

    public static ArrayList<Node> list = new ArrayList<>();

    public static void kLevelFarPrint(Node node, int data,int k) {
        nodeToRootPath(node, data);
        for (int i = 0; i < list.size(); i++) {
            kLevelDownPrint(list.get(i), k-i, i == 0 ? null : list.get(i - 1));
        }
    }

    public static void kLevelDownPrint(Node node, int k, Node nodeToSkip) {
        if (node == null || k < 0 || node.data == nodeToSkip.data) {
            return;
        }
        if (k == 0) {
            System.out.println(node.data + " ");
        }
        kLevelDownPrint(node.left, k - 1, nodeToSkip);
        kLevelDownPrint(node.right, k - 1, nodeToSkip);

    }

    public static boolean nodeToRootPath(Node node, int k) {

        if (node == null) {
            return false;
        }

        if (node.data == k) {
            list.add(node);
            return true;
        }
        boolean left = nodeToRootPath(node.left, k);
        if (left) {
            list.add(node);
            return true;
        }
        boolean right = nodeToRootPath(node.right, k);
        if (right) {
            list.add(node);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Integer[] arr = {3,5,1,6,2,0,8,null,null,7,4};
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
        kLevelFarPrint(root,5, 2);
    }
}
