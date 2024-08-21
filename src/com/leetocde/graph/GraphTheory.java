package com.leetocde.graph;/**
 * @author zhf
 * @date 2024/3/20 9:34
 * @version 1.0
 */

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：图论
 * @date 2024/3/20 9:34
 **/
public class GraphTheory {

    /**
     * 797: 所有可能路径
     * @param graph
     * @return
     */
    List<List<Integer>> ret_797 = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs_797(graph, path);
        return ret_797;
    }

    private void dfs_797(int[][] graph, List<Integer> path) {
        int lastIndex = path.size()-1;
        if(path.get(lastIndex) == graph.length-1){
            ret_797.add(new ArrayList<>(path));
            return;
        }
        int[] temp = graph[path.get(lastIndex)];
        for (int i : temp) {
            path.add(i);
            dfs_797(graph,path);
            path.remove(lastIndex+1);
        }
    }


    /**
     * 200: 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    bfs_200(grid,visited,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs_200(char[][] grid, boolean[][] visited, int i, int j) {
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == '1' && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == '1' && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == '1' && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == '1' && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
            }
        }
    }

    class ArrayIndex {
        int row;
        int col;

        public ArrayIndex(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    /**
     * 695：岛屿的最大面积
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(bfs_695(grid,visited,i,j),max);
                }
            }
        }
        return max;
    }


    private int bfs_695(int[][] grid, boolean[][] visited, int i, int j) {
        int area = 1;
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == 1 && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
                area++;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == 1 && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
                area++;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == 1 && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
                area++;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == 1 && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
                area++;
            }
        }
        return area;
    }


    /**
     * 1020: 飞地数量
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    count += bfs_1020(grid,visited,i,j);
                }
            }
        }
        return count;
    }

    private int bfs_1020(int[][] grid, boolean[][] visited, int i, int j) {
        int area = 1;
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        boolean isEdge = false;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 >= grid.length || temp.row-1 < 0 || temp.col+1 >= grid[0].length || temp.col-1 < 0) isEdge = true;
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == 1 && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
                area++;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == 1 && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
                area++;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == 1 && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
                area++;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == 1 && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
                area++;
            }
        }
        if(isEdge) return 0;
        return area;
    }


    /**
     * 130: 被围绕的区域
     * @param board
     */
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O' && !visited[i][j]) {
                    bfs_1020(board,visited,i,j);
                }
            }
        }
    }

    private void bfs_1020(char[][] grid, boolean[][] visited, int i, int j) {
        Queue<ArrayIndex> queue = new LinkedList<>();
        Queue<ArrayIndex> queueChange = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        queueChange.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        boolean isEdge = false;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 >= grid.length || temp.row-1 < 0 || temp.col+1 >= grid[0].length || temp.col-1 < 0) isEdge = true;
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == 'O' && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                queueChange.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == 'O' && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                queueChange.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == 'O' && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                queueChange.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == 'O' && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                queueChange.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
            }
        }
        if(!isEdge){
            while(!queueChange.isEmpty()){
                ArrayIndex change = queueChange.poll();
                grid[change.row][change.col] = 'X';
            }
        };
    }


    /**
     * 417: 太平洋大西洋水流问题
     * @param heights
     * @return
     */

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if(bfs_417(heights,i,j)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    ret.add(temp);
                }
            }
        }
        return ret;
    }

    private boolean bfs_417(int[][] heights, int i, int j) {
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        boolean pac = false;
        boolean atl = false;
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        while (!queue.isEmpty() &&( !pac || !atl)){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 >= heights.length || temp.col+1 >= heights[0].length){
                atl = true;
            }
            if(temp.row-1 < 0 || temp.col-1 < 0){
                pac = true;
            }
            if(temp.row+1 < heights.length && heights[temp.row+1][temp.col] <=heights[temp.row][temp.col] && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
            }
            if(temp.row-1 >= 0 && heights[temp.row-1][temp.col] <=heights[temp.row][temp.col] && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
            }
            if(temp.col+1 < heights[0].length && heights[temp.row][temp.col+1] <=heights[temp.row][temp.col] && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
            }
            if(temp.col-1 >= 0 && heights[temp.row][temp.col-1] <=heights[temp.row][temp.col] && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
            }
        }
        if(atl && pac) return true;
        return false;
    }


    /**
     * 827: 最大人工岛
     * @param grid
     * @return
     */
    public int largestIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Map<Integer,Integer> area = new HashMap<>();
        int number = 2;
        int max = 0;
        //1 记录所有岛的面积
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    int temp = bfs_827(number,grid,visited,i,j);
                    area.put(number,temp);
                    max = Math.max(max,temp);
                    number++;
                }
            }
        }
        //2 人造岛

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    max = Math.max(max, connectIsland(grid,area,i,j));
                }
            }
        }
        return max;
    }

    private int connectIsland(int[][] grid, Map<Integer, Integer> area, int i, int j) {
        int sum = 1;
        Map<Integer,Integer> temp = new HashMap<>();
        if(i+1 < grid.length && grid[i+1][j] != 0 ){
            temp.put(grid[i+1][j],grid[i+1][j]);
        }
        if(i-1 >= 0 && grid[i-1][j] != 0 ){
            temp.put(grid[i-1][j],grid[i-1][j]);
        }
        if(j+1 < grid[0].length && grid[i][j+1] != 0 ){
            temp.put(grid[i][j+1],grid[i][j+1]);
        }
        if(j-1 >= 0 && grid[i][j-1] != 0 ){
            temp.put(grid[i][j-1],grid[i][j-1]);
        }
        for (Integer integer : temp.keySet()) {
            sum += area.get(integer);
        }
        return sum;
    }


    private int bfs_827(int number, int[][] grid, boolean[][] visited, int i, int j) {
        int area = 1;
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        grid[i][j] = number;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == 1 && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
                grid[temp.row+1][temp.col] = number;
                area++;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == 1 && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
                grid[temp.row-1][temp.col] = number;
                area++;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == 1 && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
                grid[temp.row][temp.col+1] = number;
                area++;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == 1 && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
                grid[temp.row][temp.col-1] = number;
                area++;
            }
        }
        return area;
    }


    /**
     * 127: 单词接龙
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    int ret_127 = 0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean[] visited = new boolean[wordList.size()];
        for (String s : wordList) {
            if(s.equals(endWord)){
                ret_127 = wordList.size()+1;
                dfs_127(1,visited,beginWord,endWord,wordList);
            }
        }
        return ret_127;
    }

    private void dfs_127(int length, boolean[] visited, String nowWord, String endWord, List<String> wordList) {
        if(length == ret_127) return;
        if(nowWord.equals(endWord)){
            ret_127 = Math.min(ret_127,length);
            return;
        }
        for (int i = 0; i < wordList.size(); i++) {
            if(isLegal(nowWord,wordList.get(i)) && !visited[i]){
                visited[i] = true;
                dfs_127(length+1, visited, wordList.get(i), endWord, wordList);
                visited[i] = false;
            }
        }
    }

    private boolean isLegal(String nowWord, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != nowWord.charAt(i) ) count++;
        }
        if (count > 1 || count ==0) return false;
        return true;
    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                // 依次遍历当前队列中的单词
                String currentWord = queue.poll();
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    /**
     * 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
     *
     * @param currentWord
     * @param endWord
     * @param queue
     * @param visited
     * @param wordSet
     * @return
     */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord,
                                             Queue<String> queue, Set<String> visited, Set<String> wordSet) {
        char[] charArray = currentWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            // 先保存，然后恢复
            char originChar = charArray[i];
            for (char k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }
                charArray[i] = k;
                String nextWord = String.valueOf(charArray);
                if (wordSet.contains(nextWord)) {
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        // 注意：添加到队列以后，必须马上标记为已经访问
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复
            charArray[i] = originChar;
        }
        return false;
    }


    /**
     * 841: 钥匙和房间
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> keys = new HashSet<>();
        keys.add(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            rooms.get(queue.poll()).forEach(key -> {
                if(!keys.contains(key)) queue.offer(key);
                keys.add(key);
            });
            if(keys.size() == rooms.size()) return true;
        }
        return false;
    }


    /**
     * 463: 岛屿的周长
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    return bfs_463(grid,visited,i,j);
                }
            }
        }
        return 0;
    }


    private int bfs_463(int[][] grid, boolean[][] visited, int i, int j) {
        int round = 0;
        Queue<ArrayIndex> queue = new LinkedList<>();
        queue.add(new ArrayIndex(i,j));
        visited[i][j] = true;
        while (!queue.isEmpty()){
            ArrayIndex temp = queue.poll();
            if(temp.row+1 < grid.length && grid[temp.row+1][temp.col] == 1 && !visited[temp.row + 1][temp.col]){
                queue.add(new ArrayIndex(temp.row+1,temp.col));
                visited[temp.row+1][temp.col] = true;
            }else if(temp.row+1 >= grid.length || grid[temp.row+1][temp.col] == 0){
                round++;
            }
            if(temp.row-1 >= 0 && grid[temp.row-1][temp.col] == 1 && !visited[temp.row - 1][temp.col]){
                queue.add(new ArrayIndex(temp.row-1,temp.col));
                visited[temp.row-1][temp.col] = true;
            }else if(temp.row-1 < 0 || grid[temp.row-1][temp.col] == 0){
                round++;
            }
            if(temp.col+1 < grid[0].length && grid[temp.row][temp.col+1] == 1 && !visited[temp.row][temp.col + 1]){
                queue.add(new ArrayIndex(temp.row,temp.col+1));
                visited[temp.row][temp.col+1] = true;
            }else if(temp.col+1 >= grid[0].length || grid[temp.row][temp.col+1] == 0){
                round++;
            }
            if(temp.col-1 >= 0 && grid[temp.row][temp.col-1] == 1 && !visited[temp.row][temp.col - 1]){
                queue.add(new ArrayIndex(temp.row,temp.col-1));
                visited[temp.row][temp.col-1] = true;
            }else if(temp.col-1 < 0 || grid[temp.row][temp.col-1] == 0){
                round++;
            }
        }
        return round;
    }

}
