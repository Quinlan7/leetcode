package com.top100;/**
 * @author zhf
 * @date 2024/3/27 8:53
 * @version 1.0
 */

import org.junit.Test;

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：热题100
 * @date 2024/3/27 8:53
 **/
public class top100 {

    /**
     * 49：字母移位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String,List<Integer>> position = new HashMap<>();
        for (int i = 0 ; i < strs.length ; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            List<Integer> positionList = position.getOrDefault(Arrays.toString(temp),new ArrayList<>());
            positionList.add(i);
            position.put(Arrays.toString(temp), positionList);
        }
        for (List<Integer> value : position.values()) {
            List<String> group = new ArrayList<>();
            for (Integer i : value) {
                group.add(strs[i]);
            }
            ret.add(group);
        }
        return ret;
    }

    /**
     * 128: 最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        int count = 1;
        int max = 1;
        int last = queue.poll();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if(now - last == 0){
                continue;
            }else if(now - last == 1){
                count++;
            }else{
                max = Math.max(max,count);
                count = 1;
            }
            last = now;
        }
        max = Math.max(max,count);
        return max;
    }

    /**
     * 283: 移动0
     * @param nums
     */
    @Test
    public void test_283(){
        moveZeroes(new int[]{0,1,0,3,12});
    }
    public void moveZeroes(int[] nums) {
        int tail = nums.length - 1;
        for (int i = 0; i < tail+1; i++) {
            if(nums[i] == 0){
                for (int j = i; j < tail; j++) {
                    nums[j] = nums[j+1];
                }
                nums[tail] = 0;
                tail--;
                i--;
            }
        }
    }


    /**
     * 11: 盛最多水的容器
     * * @param height
     * @return
     */
    // 暴力解，超时
    public int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                max = Math.max(max,Math.min(height[i],height[j]) * (j-i));
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            max = Math.max(max,Math.min(height[left],height[right]) * (right-left));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }


    /**
     * 42: 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < height.length; i++) {
            if(height[i-1] > height[i]) stack.push(i-1);
            if(height[i-1] < height[i]){
                if(stack.isEmpty()) continue;

                int min = Math.min(height[i],height[stack.peek()]);
                count += (i-stack.peek()-1) * (min - height[i-1]);
                for (int j = stack.peek()+1; j < i; j++) {
                    height[j] = min;
                }
                if(height[stack.peek()] < height[i]) i--;
                if(height[stack.peek()] <= height[i]) stack.pop();

            }
        }
        return count;
    }


    /**
     * 3: 无重复字符的最长字串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        Map<Character,Integer> map = new HashMap<>();
        map.put(s.charAt(0),0);
        int left = 0,right = 1;
        int max = 1;
        for (; right < s.length(); ) {
            char temp = s.charAt(right);
            if(!map.containsKey(temp)){
                map.put(temp,right);
                right++;
                max = Math.max(right - left,max);
            }else {
                int repeat = map.get(temp);
                for (int i = left; i < repeat; i++) {
                    map.remove(s.charAt(i));
                }
                left = repeat+1;
                map.put(temp,right);
                right++;
            }
        }
        return max;
    }


    /**
     * 438: 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length()) return new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        Map<Character,Integer> pCount = new HashMap<>();
        Map<Character,Integer> sCount = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            pCount.put(p.charAt(i),pCount.getOrDefault(p.charAt(i),0)+1);
        }
        for (int i = 0; i < p.length(); i++) {
            sCount.put(s.charAt(i),sCount.getOrDefault(s.charAt(i),0)+1);
        }
        int left = 0,right = p.length();
        for (; right < s.length(); right++,left++) {
            if(pCount.equals(sCount)) ret.add(left);
            char leftChar = s.charAt(left);
            if(sCount.get(leftChar) == 1) sCount.remove(leftChar);
            else sCount.put(leftChar,sCount.get(leftChar)-1);
            sCount.put(s.charAt(right),sCount.getOrDefault(s.charAt(right),0)+1);
        }
        if(pCount.equals(sCount)) ret.add(left);
        return ret;
    }


    /**
     * 560: 和为k的子数组
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 1) return nums[0] == k  ? 1:0;
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k) ret++;
            }
        }
        return ret;
    }


    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    /**
     * 76: 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        Map<Character,Integer> tCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCount.put(t.charAt(i), tCount.getOrDefault(t.charAt(i),0)+1 );
        }
        Map<Character,Integer> sCount = new HashMap<>();
        Map<Character,Queue<Integer>> index = new HashMap<>();
        String ret = "" ;
        for (int i = 0; i < s.length(); i++) {
            if(tCount.containsKey(s.charAt(i)) && sCount.getOrDefault(s.charAt(i),0) < tCount.getOrDefault(s .charAt(i),0)){
                Queue<Integer> queue = index.getOrDefault(s.charAt(i), new LinkedList<>());
                queue.offer(i);
                index.put(s.charAt(i) , queue );
                sCount.put(s.charAt(i), sCount.getOrDefault(s.charAt(i),0)+1 );
            } else if (tCount.containsKey(s.charAt(i)) && Objects.equals(sCount.getOrDefault(s.charAt(i), 0), tCount.getOrDefault(s.charAt(i), 0))) {
                Queue<Integer> queue = index.getOrDefault(s.charAt(i), new LinkedList<>());
                queue.offer(i);
                queue.poll();
                index.put(s.charAt(i) , queue );
            }
            if(tCount.equals(sCount)){
                int begin = index
                        .values()
                        .stream()
                        .map(Queue::peek)
                        .min(Comparator.naturalOrder())
                        .get();
                if (ret.equals("") || i+1 - begin < ret.length()) {
                    ret = s.substring(begin,i+1);
                }
                sCount.put(s.charAt(begin) , sCount.get(s.charAt(begin))-1 );
                Queue<Integer> queue = index.getOrDefault(s.charAt(begin), new LinkedList<>());
                queue.poll();
                index.put(s.charAt(begin) , queue );
            }
        }
        return ret;
    }


    /**
     * 189: 轮转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 41:缺失的第一个正数
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) priorityQueue.offer(nums[i]);
        }
        int ret = 0;
        int temp = 0;
        if(priorityQueue.isEmpty()) return 1;
        do {
            ret++;
            while( !priorityQueue.isEmpty() && priorityQueue.peek() < ret){
                priorityQueue.poll();
            }
            if(priorityQueue.isEmpty()) return ret;
            temp = priorityQueue.poll();
        }while(temp == ret && !priorityQueue.isEmpty());
        return temp == ret? ret+1 : ret ;
    }

    public int firstMissingPositive2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int ret = 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == ret) ret++;
            else if(nums[i] > 0) set.add(nums[i]);
        }
        while(!set.isEmpty()){
            if(set.contains(ret)) {
                ret++;
                continue;
            }else return ret;
        }
        return ret;
    }

    /**
     * 常数的空间复杂度，O(n)的时间复杂度
     * 其实无论如何都需要一个 哈希表 来存储状态。但是我们可以修改 输入数组！！！！
     * @param nums
     * @return
     */
    public int firstMissingPositive3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] <= 0) nums[i] = nums.length+1;
        }
        for (int i = 0; i < nums.length; i++) {
            if(Math.abs(nums[i]) <= nums.length) nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) return i+1;
        }
        return nums.length+1;
    }


    /**
     * 73: 矩阵置零
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int flag = -11;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == flag ) matrix[i][j] = Math.abs(matrix[i][j]);
                if(matrix[i][j] == 0) matrix[i][j] = flag;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == flag){
                    matrix[i][j] = 0;
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[k][j] != flag) {
                            matrix[k][j] = 0;
                        }
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (matrix[i][k] != flag) {
                            matrix[i][k] = 0;
                        }
                    }
                }
            }
        }

    }


    /**
     * 54: 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int i = 0;
        int j = 0;
        int count = matrix.length * matrix[0].length;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        // false + , true -
        // flag false 列，true 行
        boolean flag = false,toggle = false;
        for ( ; ret.size() < count; ) {
            for ( ; ret.size() < count; ) {
                ret.add(matrix[i][j]);
                visited[i][j] = true;
                //列的变化
                if(!flag){
                    // ++
                    if (!toggle) {
                        // 未达到边界
                        if( j < matrix[0].length-1 && !visited[i][j+1]) j++;
                        // 达到边界
                        else  {
                            i++;
                            flag = true;
                        }
                    }else {
                        // 未达到边界
                        if( j > 0 && !visited[i][j-1]) j--;
                        // 达到边界
                        else  {
                            i--;
                            flag = true;
                        }
                    }
                }else {
                    // ++
                    if (!toggle) {
                        // 未达到边界
                        if( i < matrix.length-1 && !visited[i+1][j]) i++;
                        // 达到边界
                        else {
                            j--;
                            flag = false;
                            toggle = true;
                        }
                    }else {
                        // 未达到边界
                        if( i > 0 && !visited[i-1][j]) i--;
                        // 达到边界
                        else {
                            j++;
                            flag = false;
                            toggle = false;
                        }
                    }
                }
            }
        }
        return ret;
    }



}
