package com.leetocde.lanqiao;/**
 * @author zhf
 * @date 2024/3/25 21:49
 * @version 1.0
 */

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：
 * @date 2024/3/25 21:49
 **/
public class lanqiao15 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        char[][] nums = new char[30][60];
        for (int i = 0; i < 30; i++) {
            nums[i] = scan.next().toCharArray();
        }
        int max = 0;
        boolean visited[][] = new boolean[30][60];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                if(!visited[i][j] && nums[i][j] == '1') {
                    max = Math.max(bfs(i,j,visited,nums), max);
                }
            }
        }
        System.out.println(max);

        scan.close();
    }

    private static int bfs(int i, int j, boolean[][] visited, char[][] nums) {
        // TODO Auto-generated method stub
        int area = 1;
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Arrays.asList(i,j)));
        visited[i][j] = true;
        while(!queue.isEmpty()) {
            int x = queue.peek().get(0);
            int y = queue.peek().get(1);
            queue.poll();
            if(x+1 < 30 && !visited[x+1][y] && nums[x+1][y] == '1') {
                queue.add(new ArrayList<>(Arrays.asList(x+1,y)) );
                visited[x+1][y] = true;
                area++;
            }
            if(x-1 >= 0 && !visited[x-1][y] && nums[x-1][y] == '1') {
                queue.add(new ArrayList<>(Arrays.asList(x-1,y)) );
                visited[x-1][y] = true;
                area++;
            }

            if(y+1 < 60 && !visited[x][y+1] && nums[x][y+1] == '1') {
                queue.add(new ArrayList<>(Arrays.asList(x,y+1)) );
                visited[x][y+1] = true;
                area++;
            }
            if(y-1 >= 0 && !visited[x][y-1] && nums[x][y-1] == '1') {
                queue.add(new ArrayList<>(Arrays.asList(x,y-1)) );
                visited[x][y-1] = true;
                area++;
            }
        }
        return area;
    }


}
