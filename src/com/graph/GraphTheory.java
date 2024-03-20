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


}
