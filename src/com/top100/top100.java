package com.top100;/**
 * @author zhf
 * @date 2024/3/27 8:53
 * @version 1.0
 */

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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


    /**
     * 48: 旋转图像
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int k = matrix.length / 2;
        int n = matrix.length;
        int x = 0, y = 0;
        // i 控制 第几环
        for (int i = 0; i < k; i++) {
            // j 控制 第几个
            for (int j = 0; j < n - 1 - 2*i; j++) {
                int nextValue = matrix[x+i + j][n-1-i];
                //上 -> 右
                matrix[x+i + j][n-1-i] = matrix[x+i][y+i + j];
                //右 -> 下
                int temp = nextValue;
                nextValue = matrix[n-1 -i ][n-1 -i -j];
                matrix[n-1 -i ][n-1 -i -j] = temp;
                //下 -> 左
                temp = nextValue;
                nextValue = matrix[n-1 -i -j][y + i];
                matrix[n-1 -i -j][y + i] = temp;
                //左 -> 上
                temp = nextValue;
//                nextValue = matrix[n-1 -i -j][y + i];
                matrix[x + i][y + i + j] = temp;
            }
        }
    }


    /**
     * 240: 搜索二位矩阵II
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        Queue<List<Integer>> queue = new LinkedList<>();
        if(matrix[0][0] == target) return true;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        List<Integer> position = new ArrayList<>();
        position.add(0);
        position.add(0);
        queue.add(position);
        visited[0][0] = true;
        while(!queue.isEmpty()){
            position = queue.poll();
            int x = position.get(0);
            int y = position.get(1);
            if (x+1 < matrix.length && !visited[x + 1][y]) {
                if(matrix[x + 1][y] == target) return true;
                else if (matrix[x + 1][y] < target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(x + 1);
                    temp.add(y);
                    queue.add(temp);
                }
                visited[x + 1][y] = true;
            }
            if (y+1 < matrix[0].length && !visited[x][y + 1]) {
                if(matrix[x][y+1] == target) return true;
                else if (matrix[x][y + 1] < target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(x);
                    temp.add(y+1);
                    queue.add(temp);
                }
                visited[x][y+1] = true;
            }
        }
        return false;
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 234: 回文链表
     * @param head
     * @return
     */

    public boolean isPalindrome(ListNode head) {
        ListNode fast = new ListNode(0,head);
        ListNode slow = new ListNode(0,head);
        // true oushu , false jishu
        boolean falg;
        while(true){
            if(fast.next == null){
                reverse(slow);
                falg = true;
                break;
            }else if(fast.next.next == null){
                reverse(slow.next);
                falg = false;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if(!falg){
            slow = slow.next;
        }
        while(slow.next != null){
            if(head.val != slow.next.val) return false;
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode pre){
        if(pre.next == null) return pre;
        ListNode last = pre.next;
        ListNode now = pre.next.next;
        if(now == null) return pre;
        while(true){
            if(now.next == null){
                now.next = pre.next;
                pre.next = now;
                last.next = null;
                return pre;
            }
            ListNode next = now.next;
            now.next = pre.next;
            pre.next = now;
            last.next = next;

            now = last.next;
        }
    }


    /**
     * 141：环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = new ListNode(0,head);
        ListNode slow = new ListNode(0,head);
        while(true){
            if(fast.next==null || fast.next.next==null) return false;
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
    }


    /**
     * 21: 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode now1 = list1;
        ListNode now2 = list2;
        if(list1 == null || list2==null) return list1==null?list2:list1;
        ListNode pre = list1.val < list2.val? list1: list2;
        boolean falg = false;
        if(list1.val < list2.val){
            falg = true;
            now1 = list1.next;
        }else now2 = list2.next;
        ListNode next1 = null;
        ListNode next2 = null;
        if (now1 != null && now2 != null) {
            next1 = now1.next;
            next2 = now2.next;
        }
        while(now1 != null && now2 != null){
            if(now1.val<now2.val){
                pre.next = now1;
                pre = now1;
                now1 = next1;
                next1 = next1==null? null:next1.next;
            }else {
                pre.next = now2;
                pre = now2;
                now2 = next2;
                next2 = next2==null? null:next2.next;
            }
        }
        if(now1 == null){
            pre.next = now2;
        }else {
            pre.next = now1;
        }

        if(falg) return list1;
        return list2;
    }


    /**
     * 2:两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int promotion = 0;
        ListNode head = new ListNode();
        ListNode last = head;
        while(l1!=null || l2 !=null || promotion!=0){
            int temp = (l1 == null? 0:l1.val) + (l2==null ? 0:l2.val) + promotion;
            ListNode listNode = new ListNode(temp % 10);
            promotion = temp / 10;
            last.next = listNode;
            last = last.next;
            l1 = l1 == null? l1:l1.next;
            l2 = l2 == null? l2:l2.next;
        }
        return head.next;
    }


    /**
     * 25:K个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    @Test
    public void test_25(){
        ListNode listNode5 = new ListNode(5,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        reverseKGroup(listNode1,2);
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode listNode = new ListNode(0,head);
        head = listNode;
        int index = 0;
        while(head != null){
            if(index % k == 0) reverseK(head,k);
            index++;
            head = head.next;
        }
        return listNode.next;
    }

    public ListNode reverseK(ListNode pre, int k){
        if(k == 1) return pre;
        int length = -1;
        ListNode listNode = pre;
        while (length < k && listNode != null){
            listNode = listNode.next;
            length++;
        }
        if(length < k) return pre;
        ListNode last = pre.next;
        ListNode now = pre.next.next;
        length = 1;
        while(length < k){
            if(now.next == null){
                now.next = pre.next;
                pre.next = now;
                last.next = null;
                return pre;
            }
            ListNode next = now.next;
            now.next = pre.next;
            pre.next = now;
            last.next = next;

            now = last.next;
            length++;
        }
        return pre;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    /**
     * 138:随机链表的赋值
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node temp = head;
        int length = 1;
        while(temp.next != null){
            length++;
            temp = temp.next;
        }
        temp = head;
        Node newHead = new Node(temp.val);
        Node newTemp = newHead;
        while(temp.next != null){
            Node node = new Node(temp.next.val);
            newTemp.next = node;
            newTemp = node;
            temp = temp.next;
        }
//        Node node = new Node(temp.val);
//        newTemp.next = node;
        newTemp = newHead;
        temp = head;
        while(newTemp!=null && temp!=null){
            Node now = temp.random;
            if(now == null) newTemp.random = null;
            int position = 0;
            while(now != null){
                position++;
                now = now.next;
            }
            position = length - position;
            Node find = newHead;
            for (int i = 0; i < position; i++) {
                find = find.next;
            }
            newTemp.random = find;
            newTemp = newTemp.next;
            temp = temp.next;

        }
        return newHead;
    }


    /**
     * 148:排序链表
     * @param head
     * @return
     */
    @Test
    public void test_148(){
        ListNode listNode5 = new ListNode(0,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(5,listNode3);
        ListNode listNode1 = new ListNode(-1,listNode2);
        mergeSortList(listNode1);
    }
    public ListNode sortList(ListNode head) {
        ListNode pre = new ListNode(0,head);
        qucikSortList(pre,null);
        return pre.next;
    }

    private void qucikSortList(ListNode pre, ListNode end) {
        if(pre ==end || pre.next == end || pre.next.next == end ) return;
        ListNode now = pre.next;
        ListNode iter = now.next;
        ListNode last = now;
        while(iter != end){
            if(iter.val < now.val){
                last.next = iter.next;
                iter.next = pre.next;
                pre.next = iter;

                iter = last.next;
            }else {
                last = last.next;
                iter = iter.next;
            }

        }
        qucikSortList(pre,now);
        qucikSortList(now,end);
    }

    public ListNode mergeSortList(ListNode head) {
        ListNode pre = head;
        int N = 0;
        while(pre != null){
            N++;
            pre = pre.next;
        }
        pre = new ListNode(0,head);
        for (int sz = 1; sz < N ; sz += sz) {
            ListNode preTemp = pre;
            for (int i = 0; i < N - sz; i += sz+sz) {
                merge(preTemp,sz,i+sz+sz>N? N-i: sz+sz);
                for (int j = 0; j < sz + sz; j++) {
                    if(preTemp.next == null) break;
                    preTemp = preTemp.next;
                }
            }
        }
        return pre.next;
    }

    private static void merge(ListNode a, int mid, int right){
        ListNode one = a.next;
        ListNode two = a.next;
        ListNode pre = a;
        int temp = mid;
        while(temp > 0) {
            two = two.next;
            temp--;
        }
        int oneCount = 0;
        int twoCount = 0;
        for (int i = 0; i < right; i++) {
            if(oneCount == mid){
                pre.next = two;
                two = two.next;
                twoCount++;
            } else if (twoCount == right - mid) {
                pre.next = one;
                one = one.next;
                oneCount++;
            } else if(one.val <= two.val){
                pre.next = one;
                one = one.next;
                oneCount++;
            }else {
                pre.next = two;
                two = two.next;
                twoCount++;
            }
            pre = pre.next;
        }
        pre.next = two;
        System.out.println("  ");
    }


    /**
     * 23: 合并k个升序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            while(lists[i] != null){
                queue.add(lists[i].val);
                lists[i] = lists[i].next;
            }
        }
        if(queue.isEmpty()) return null;
        ListNode ret = new ListNode(queue.poll());
        ListNode temp = ret;
        while(!queue.isEmpty()){
            temp.next = new ListNode(queue.poll());
            temp = temp.next;
        }
        return ret;
    }


    /**
     * 146: LRU缓存
     */
    class LRUCache {

        int capacity;
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            int val = map.getOrDefault(key,-1);
            if(val != -1){
                map.remove(key);
                map.put(key,val);
            }
            return val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)){
                map.remove(key);
                map.put(key,value);
                return;
            }
            if(map.size()<capacity){
                map.put(key,value);
                return;
            }
            map.remove(map.entrySet().iterator().next().getKey());
            map.put(key,value);

        }
    }


    class LRUCache2 extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }




}
