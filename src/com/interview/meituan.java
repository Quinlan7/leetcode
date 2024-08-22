package com.interview;

import java.util.*;
import java.io.*;

public class meituan {

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/e03f133cbabb41c8abfc3c7d4137bc6f?examPageSource=%E5%85%AC%E5%8F%B8%E7%9C%9F%E9%A2%98%E7%AD%94%E6%A1%88#jsEditorModuleBody
     * 来源：牛客网
     *
     * 小美认为，在人际交往中，但是随着时间的流逝，朋友的关系也是会慢慢变淡的，最终朋友关系就淡忘了。
     * 现在初始有一些朋友关系，存在一些事件会导致两个人淡忘了他们的朋友关系。小美想知道某一时刻中，某两人是否可以通过朋友介绍互相认识？
     * 事件共有 2 种：
     * 1 u v：代表编号 u 的人和编号 v 的人淡忘了他们的朋友关系。
     * 2 u v：代表小美查询编号 u 的人和编号 v 的人是否能通过朋友介绍互相认识。
     *
     * 注：介绍可以有多层，比如 2 号把 1 号介绍给 3 号，然后 3 号再把 1 号介绍给 4 号，这样 1 号和 4 号就认识了。
     * @param args
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        // 注意 hasNext 和 hasNextLine 的区别

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int q = Integer.parseInt(firstLine[2]);

        long MOD = 1000000001;
        Set<Long> edgesSet = new HashSet<>();
        while (m-- > 0) {
            String[] edgeInfo = reader.readLine().split(" ");
            int node1 = Integer.parseInt(edgeInfo[0]);
            int node2 = Integer.parseInt(edgeInfo[1]);
            edgesSet.add(node1 * MOD + node2);
        }
        int[][] ops = new int[q][3];
        while (q-- > 0) {
            String[] opInfo = reader.readLine().split(" ");
            ops[q][0] = Integer.parseInt(opInfo[0]);
            ops[q][1] = Integer.parseInt(opInfo[1]);
            ops[q][2] = Integer.parseInt(opInfo[2]);
            if (ops[q][0] == 1) {
                long edgeNum1 = ops[q][1] * MOD + ops[q][2];
                long edgeNum2 = ops[q][2] * MOD + ops[q][1];
                if (edgesSet.contains(edgeNum1)) {
                    edgesSet.remove(edgeNum1);
                } else if (edgesSet.contains(edgeNum2)) {
                    edgesSet.remove(edgeNum2);
                } else {
                    ops[q][0] = 3;
                }
            }
        }

        UnionFind uf = new UnionFind(n);
        for (long edge : edgesSet) {
            int a = (int) (edge / MOD);
            int b = (int) (edge % MOD);
            uf.union(a - 1, b - 1);
        }
        List<Boolean> list = new ArrayList<>();
        for (int[] op : ops) {
            if (op[0] == 1) {
                uf.union(op[1] - 1, op[2] - 1);
            } else if (op[0] == 2) {
                list.add(uf.find(op[1] - 1) == uf.find(op[2] - 1));
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            writer.write(list.get(i) ? "Yes\n" : "No\n");
        }
        writer.flush();

    }



    static class UnionFind {
        Map<Integer, Integer> repre;
        Map<Integer, Integer> rank;

        public UnionFind(int n) {
            repre = new HashMap<>();
            rank = new HashMap<>();
        }

        public int find(int x) {
            int xParent = repre.getOrDefault(x, x);
            if (xParent != x) {
                repre.put(x, find(xParent));
            }
            return repre.getOrDefault(x, x);
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            int xRank = rank.getOrDefault(rootX, 0);
            int yRank = rank.getOrDefault(rootY, 0);
            if (xRank < yRank) {
                repre.put(rootX, rootY);
            } else if (xRank > yRank) {
                repre.put(rootY, rootX);
            } else {
                repre.put(rootX, rootY);
                rank.put(rootY, yRank + 1);
            }
        }
    }


//    链接：https://www.nowcoder.com/questionTerminal/d7f4f96f29b14feb973e7ad8ca74a8b7?examPageSource=%E5%85%AC%E5%8F%B8%E7%9C%9F%E9%A2%98%E7%AD%94%E6%A1%88#jsEditorModuleBody
//    来源：牛客网

    /**
     * 小美的区间删除
     * 小美拿到了一个大小为n的数组，她希望删除一个区间后，使得剩余所有元素的乘积末尾至少有k个 0。小美想知道，一共有多少种不同的删除方案？
     * @param args
     * @throws IOException
     */

    public static void main2  (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstStr = reader.readLine().split(" ");
        int n = Integer.parseInt(firstStr[0]);
        int k = Integer.parseInt(firstStr[1]);
        int[] nums = new int[n];
        String[] strs = reader.readLine().split(" ");
        int countSec[] = new int[n];
        int countFive[] = new int[n];
        int totalSec = 0;
        int totalFivr = 0;
        for(int i = 0 ; i < n ; i++){
            nums[i] = Integer.parseInt(strs[i]);
            int tempSec = nums[i],tempFive = nums[i];
            while(tempSec % 2 == 0){
                countSec[i]++;
                tempSec /= 2;
                totalSec++;
            }
            while(tempFive % 5 == 0){
                countFive[i]++;
                tempFive /= 5;
                totalFivr++;
            }
        }
        /*
    思路 :  滑动窗口
    因为 元素 都大于 1  所以 所有的 0  只能来自 与 2  5 两个因子
    例如  25 6  15 4  等 即5 的倍数 或者 2 的倍数  二者相乘 才能多出1个 零
    或者 本身既是2的倍数 又是 五的倍数 那就是 本身就 携带零的;
     */

    /* 滑动窗口统计的是可以删除的 区间 有几个
     依旧 有 dp的思想在 例如 只有一个 元素 的区间
     1  可删除的数字就一个
     1 2  可删除的 区间 1 , 2  , 1 2  三个
     1 2 3 可删除区间   1 ,2 ,1 2, 3 ,2 3,1 2 3  六个
       就可以发现规律 了 从后向前 看  例如 多出一个3 的时候  可删除的区间
         多了  3 ,2 3,1 2 3  这三个 再加上 只有 1 2 两个数字时可组成的区间
         这样 从前向后推导
         dp[i]  含义  共 i个数 可组成的连续子序列共 dp[i]个
         dp[1]=1  dp[i]=dp[i-1]+i
     */

        long count = 0;
        int l = 0;
        for(int r = 0 ; r < n ; r++){
            totalSec -= countSec[r];
            totalFivr -= countFive[r];
            while(Math.min(totalFivr,totalSec) < k && l <= r){
                totalFivr += countFive[l];
                totalSec += countSec[l];
                l++;
            }
            count += r - l + 1;
        }
        System.out.println(count);

    }


    /**
     * 小美的平衡矩阵：滑动窗口
     * 小美拿到了一个 n∗n 的矩阵，其中每个元素是 0 或者 1。
     * 小美认为一个矩形区域是完美的，当且仅当该区域内 0 的数量恰好等于 1 的数量。
     * 现在，小美希望你回答有多少个 i∗i 的完美矩形区域。你需要回答 1≤i≤n 的所有答案。
     * @param args
     */
    public static void main3(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] nums = new int[n][n];
        String[] temp = new String[n];
        for (int i = 0; i < n; i++) {
            temp[i] = in.next();
            for (int j = 0; j < n; j++) {
                nums[i][j] = temp[i].charAt(j) - 48;
            }
        }
        int[] ret = new int[n];
        for (int i = 1; i < n + 1; i++) {
            if ((i * i) % 2 == 1) {
                ret[i - 1] = 0;
                System.out.println(ret[i - 1]);
                continue;
            }
            int target = (i * i) / 2;
            int sum = 0;

            // 二维滑动窗口
            for (int j = 0; j < n - i + 1; j++) {
                // 初始化 sum
                sum = 0;
                for (int jr = j; jr < j + i; jr++) {
                    for (int k = 0; k < i; k++) {
                        sum += nums[jr][k];
                    }
                }
                if (sum == target) {
                    ret[i - 1]++;
                }
                for (int k = i; k < n; k++) {
                    for (int m = j; m < j + i; m++) {
                        sum -= nums[m][k - i];
                        sum += nums[m][k];
                    }
                    if (sum == target) {
                        ret[i - 1]++;
                    }

                }
            }
            System.out.println(ret[i - 1]);

        }
    }

//    链接：https://www.nowcoder.com/questionTerminal/d3a6268bbbf743f6b7441eb7ab30633e?examPageSource=%E5%85%AC%E5%8F%B8%E7%9C%9F%E9%A2%98%E7%AD%94%E6%A1%88#jsEditorModuleBody
//    来源：牛客网

    /**
     * 小美的平衡矩阵：前缀和
     * @param args
     */
    public static void main4(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] rectInt = new int[n][n];
        String[] temp = new String[n];
        for (int i = 0; i < n; i++) {
            temp[i] = in.next();
            for (int j = 0; j < n; j++) {
                rectInt[i][j] = temp[i].charAt(j) - 48;
            }
        }
        // 使用 二维 数组的前缀和  之所以 +1 是为了统一 操作, 避免判断边界
        //如果 不用 n+1 而使用 n  则需要手动初始化 第一行和第一列
        int[][] preSum = new int[n + 1][n + 1];
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=n ; j++) {
                preSum[i][j]=preSum[i][j-1]+preSum[i-1][j]-preSum[i-1][j-1]+rectInt[i-1][j-1];
            }
        }
        for (int k = 1; k <= n; k++) {  //控制矩形大小
            if (k%2!=0){  // 奇数个元素 0 和 1 的数量不可能相等
                System.out.println(0);
                continue;
            }
            int res=0;
            int target=(k*k)/2;
            for (int i = k; i <=n ; i++) {
                for (int j = k; j <=n; j++) {
                    //计算每一个小正方形的和
                    int sum=preSum[i][j]-preSum[i-k][j]-preSum[i][j-k]+preSum[i-k][j-k];
                    if (sum==target){
                        res++;
                    }
                }
            }
            System.out.println(res);
        }
    }





}
