package binaryTree;

import java.util.Stack;

public class IterativeilyPreInPostTraversalBinaryTree {

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

    public static void preTraversalIteratively(Node node) {
        Stack<Pair> stack = new Stack<>();
        Pair pair = new Pair(node, 1);
        stack.push(pair);
        String pre = "";
        String post = "";
        String in = "";
        while (stack.size() > 0) {
            Pair top = stack.peek();
            if (top.state == 1) {
                pre += top.data.data + " ";
                top.state++;
                if(top.data.left!=null){
                    stack.push(new Pair(top.data.left,1));
                }
            } else if (top.state == 2) {
                in += top.data.data+" ";
                top.state++;
                if(top.data.right!=null){
                    stack.push(new Pair(top.data.right,1));
                }
            } else {
                post += top.data.data+" ";
                stack.pop();
            }
        }
        System.out.println("pre::"+ pre);
        System.out.println("in::"+ in);
        System.out.println("post::"+ post);
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
        preTraversalIteratively(root);



    }
}
