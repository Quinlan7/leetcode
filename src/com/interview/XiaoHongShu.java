package com.interview;

import java.io.*;
import java.util.*;


public class XiaoHongShu {

    private static final int MUL = 5000;
    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int m = Integer.parseInt(strFir[1]);
        char[][] grip = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            grip[i] = row.toCharArray();
        }
        boolean[][] reachable = new boolean[n][m];
        for (boolean[] row : reachable) {
            Arrays.fill(row, true);
        }
        int ret = n * m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(check(grip,reachable,i,j)){
                    ret--;
                }
            }
        }
        writer.write(ret + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }

    private static boolean check(char[][] grip, boolean[][] reachable,int i,int j) {
        List<Integer> stepRecord = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * MUL + j);
        stepRecord.add(i * MUL + j);
        boolean[][] visited = new boolean[grip.length][grip[0].length];
        visited[i][j] = true;
        while(!queue.isEmpty()){
            int temp = queue.poll();
            int x = temp / MUL;
            int y = temp % MUL;
            if(grip[x][y] == 'L'){
                if(y - 1 < 0){
                    return true;
                }
                if(visited[x][y-1] || !reachable[x][y-1]){
                    mark(stepRecord,reachable);
                    return false;
                }
                visited[x][y-1] = true;
                queue.add(x * MUL + (y - 1));
                stepRecord.add(x * MUL + (y - 1));
            }else if(grip[x][y] == 'R'){
                if(y + 1 >= grip[0].length){
                    return true;
                }
                if(visited[x][y+1] || !reachable[x][y+1]){
                    mark(stepRecord,reachable);
                    return false;
                }
                visited[x][y+1] = true;
                queue.add(x * MUL + (y + 1));
                stepRecord.add(x * MUL + (y + 1));
            }else if(grip[x][y] == 'U'){
                if(x - 1 < 0){
                    return true;
                }
                if(visited[x - 1][y] || !reachable[x - 1][y]){
                    mark(stepRecord,reachable);
                    return false;
                }
                visited[x - 1][y] = true;
                queue.add((x - 1) * MUL + y);
                stepRecord.add((x - 1) * MUL + y);
            }else if(grip[x][y] == 'D'){
                if(x + 1 >= grip.length){
                    return true;
                }
                if(visited[x + 1][y] || !reachable[x + 1][y]){
                    mark(stepRecord,reachable);
                    return false;
                }
                visited[x + 1][y] = true;
                queue.add((x + 1) * MUL + y);
                stepRecord.add((x + 1) * MUL + y);
            }
        }
        return true;


    }

    private static void mark(List<Integer> stepRecord, boolean[][] reachable) {
        for (int i = 0; i < stepRecord.size(); i++) {
            int temp = stepRecord.get(i);
            int x = temp / MUL;
            int y = temp % MUL;
            reachable[x][y] = false;
        }
    }


    public static void main3(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        String[] strFir = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strFir[i]);
        }
        int maxLengh = 1;
        int currentLength = 0;
        Arrays.sort(a);
        Set<Integer> set = new HashSet<>();
        int lastIndex = -1;
        for (int j = 0; j < n ; j ++) {
            if(set.contains(j)) continue;
            if(lastIndex == -1){
                lastIndex = j;
                currentLength++;
                set.add(j);
            }
            for (int i = j+1; i < n; i++) {
                if(isNext(a[lastIndex],a[i])){
                    currentLength++;
                    lastIndex = i;
                    set.add(i);
                }
            }
            maxLengh = Math.max(maxLengh,currentLength);
            currentLength = 0;
            lastIndex = -1;
        }

        writer.write(maxLengh + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }

    private static boolean isNext(int x,int y){


        int target = Integer.bitCount(x);
        int next = x + 1;
        while(Integer.bitCount(next) != target){
            next++;
        }
        if(next == y) return true;
        return false;
    }




    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        String[] strFir = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strFir[i]);
        }
        Arrays.sort(a);
        long ret = 0;
        long t = n;
        for (int i = n-1; i >= 0 ; i--) {
            long temp = a[i]  * t;
            ret += temp;
            if( i - 1 >= 0){
                temp = a[--i] * t;
                ret += temp;
            }
            n = n - 2;
            t = t + n;
        }

        writer.write(ret + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }

    static class Edge implements Comparable<Edge> {
        int city1, city2;
        double distance;

        public Edge(int city1, int city2, double distance) {
            this.city1 = city1;
            this.city2 = city2;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                return true;
            }
            return false;
        }
    }

    public static int minYearsToConnectCities(int n, int[][] cities) {
        // 创建所有可能的边
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(Math.pow(cities[i][0] - cities[j][0], 2) + Math.pow(cities[i][1] - cities[j][1], 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        // 按距离从小到大排序
        Collections.sort(edges);

        // 初始化 Union-Find
        UnionFind uf = new UnionFind(n);
        double totalDistance = 0.0;

        // Kruskal算法构建最小生成树
        for (Edge edge : edges) {
            if (uf.union(edge.city1, edge.city2)) {
                totalDistance += edge.distance;
            }
        }

        // 向上取整总距离，返回整年数
        return (int) Math.ceil(totalDistance);
    }

    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cities = new int[n][2];
        for (int i = 0; i < n; i++) {
            cities[i][0] = scanner.nextInt();
            cities[i][1] = scanner.nextInt();
        }

        // 计算并输出结果
        int result = minYearsToConnectCities(n, cities);
        System.out.println(result);
    }

}
