package com.graph;/**
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







}
