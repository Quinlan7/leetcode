package com.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class yuanbao {

    static class Node {
        int color;
        List<Node> children;

        public Node(int color) {
            this.color = color;
            children = new ArrayList<>();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = scanner.nextInt();
        }
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node(0));
        }
        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            nodes.get(u).children.add(nodes.get(v));
            nodes.get(v).children.add(nodes.get(u));
        }
        for (int i = 0; i < n; i++) {
            nodes.get(i + 1).color = colors[i];
        }
        int result = dfs(nodes.get(1), null);
        System.out.println(result);
    }

    public static int dfs(Node node, Node parent) {
        int[] subtrees = new int[1000];
        for (Node child : node.children) {
            if (child!= parent) {
                int subtree = dfs(child, node);
                for (int color = 0; color < 1000; color++) {
                    subtrees[color] += subtree;
                }
            }
        }
        subtrees[node.color]++;
        int maxCount = 0;
        int bestXor = 0;
        for (int color = 0; color < 1000; color++) {
            if (subtrees[color] > maxCount) {
                maxCount = subtrees[color];
                bestXor = color;
            } else if (subtrees[color] == maxCount) {
                bestXor = 0;
            }
        }
        return bestXor;
    }


}
