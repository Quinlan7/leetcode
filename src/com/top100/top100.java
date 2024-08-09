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
     * @param
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
     * @param
     * @param
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
     * @param
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


     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }


    /**
     * 543: 二叉树的直径
     * @param root
     * @return
     */
    int ret_543 = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        getNodeDiameter(root);
        return ret_543;
    }

    private int getNodeDiameter(TreeNode root) {
        if(root == null) return 0;
        int L = getNodeDiameter(root.left);
        int R = getNodeDiameter(root.right);
        ret_543 = Math.max(ret_543,L+R);
        return Math.max(L,R)+1;
    }


    /**
     * 230: 二叉搜索树中第k小的元素
     * @param root
     * @param k
     * @return
     */
    int ret_230 = -1;
    int count_230;
    public int kthSmallest(TreeNode root, int k) {
        mediumOrderTraversal(root,k);
        return ret_230;
    }

    private void mediumOrderTraversal(TreeNode root, int k) {
        if(root == null) return ;
        mediumOrderTraversal(root.left,k);
        if(count_230 == k) {
            return;
        }
        count_230++;
        if(count_230 == k) {
            ret_230 = root.val;
            return;
        }
        mediumOrderTraversal(root.right,k);
    }

    /**
     * 114: 二叉树展开为链表
     * @param
     */
    @Test
    public void test_114(){
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5,null,treeNode6);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2,treeNode3,treeNode4);
        TreeNode treeNode1 = new TreeNode(1,treeNode2,treeNode5);
        TreeNode treeNode = new TreeNode();
        flatten(treeNode1);
    }
    public void flatten(TreeNode root) {
        if(root.left == null){
            if(root.right == null) return;
            flatten(root.right);
            return;
        }
        TreeNode left = root.left;
        while(left.right != null){
            left = left.right;
        }
        left.right = root.right;
        root.right = root.left;
        root.left = null;
        flatten(root.right);
    }


    /**
     * 105: 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        if(preorder.length == 1) return new TreeNode(preorder[0]);
        int i = 0;
        for(; i < preorder.length ; ){
            if(inorder[i] == preorder[0]) break;
            i++;
        }
        TreeNode root = new TreeNode(inorder[i]);
        root.left = buildTree( Arrays.copyOfRange(preorder,1,i+1) , Arrays.copyOfRange(inorder,0,i));
        root.right = buildTree( Arrays.copyOfRange(preorder, i+1 , preorder.length) , Arrays.copyOfRange(inorder, i + 1, inorder.length));
        return root;
    }


    /**
     * 437: 路径总和 III
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        int count = 0;
        count += pathSumRoot(root,targetSum,0L);
        count += pathSum(root.left,targetSum);
        count += pathSum(root.right,targetSum);
        return count;
    }

    private int pathSumRoot(TreeNode root,int targetSum,long sum){
        if(root == null) return 0;
        int count = 0;
        sum += root.val;
        count += pathSumRoot(root.left,targetSum,sum);
        count += pathSumRoot(root.right,targetSum,sum);
        if(sum == targetSum) count++;
        return count;
    }

    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }


    int max = -Integer.MAX_VALUE;

    /**
     * 124: 二叉树中的最大路径和
     * @param
     * @return
     */
    @Test
    public void test_124(){
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5,null,treeNode6);
//        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(1,treeNode2,treeNode3);
        TreeNode treeNode = new TreeNode();
        maxPathSum(treeNode1);
    }
    public int maxPathSum(TreeNode root) {
        sumsum(root);
        return max;
    }

    public int sumsum(TreeNode root) {
        if(root == null) return 0;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        int temp = left + right + root.val;
        int bigOne = Math.max(left,right);
        if(root.val < 0 && (left != 0 || right != 0) && temp < bigOne){
            max = Math.max(max,bigOne);
        }else {
            max = Math.max(max,temp);
        }
        return Math.max(bigOne + root.val, 0);
    }


    /**
     * 994: 腐烂的橘子
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int ret = BFS(grid);
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == 1) return -1;
            }
        }
        return ret;
    }

    private int BFS(int[][] grid){
        Queue<Map<String,Integer>> queue = new LinkedList<>();
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[0].length ; j++){
                if(grid[i][j] == 2){
                    Map<String , Integer> map = new HashMap<>();
                    map.put("x",i);
                    map.put("y",j);
                    map.put("min",0);
                    queue.offer(map);
                }
            }
        }
        int minutes = 0;
        while(!queue.isEmpty()){
            Map<String,Integer> map = queue.poll();
            int x = map.get("x");
            int y = map.get("y");
            minutes = map.get("min");
            if( x+1 < grid.length && grid[x+1][y] == 1){
                Map<String , Integer> temp = new HashMap<>();
                grid[x+1][y] = 2;
                temp.put("x",x+1);
                temp.put("y",y);
                temp.put("min",minutes+1);
                queue.offer(temp);
            }
            if( x-1 >= 0 && grid[x-1][y] == 1){
                Map<String , Integer> temp = new HashMap<>();
                grid[x-1][y] = 2;
                temp.put("x",x-1);
                temp.put("y",y);
                temp.put("min",minutes+1);
                queue.offer(temp);
            }
            if( y+1 < grid[0].length && grid[x][y+1] == 1){
                Map<String , Integer> temp = new HashMap<>();
                grid[x][y+1] = 2;
                temp.put("x",x);
                temp.put("y",y+1);
                temp.put("min",minutes+1);
                queue.offer(temp);
            }
            if( y-1 >= 0 && grid[x][y-1] == 1){
                Map<String , Integer> temp = new HashMap<>();
                grid[x][y-1] = 2;
                temp.put("x",x);
                temp.put("y",y-1);
                temp.put("min",minutes+1);
                queue.offer(temp);
            }
        }
        return minutes;
    }


    List<List<Integer>> graph;
    int[] visited;
    boolean notCircle = true;

    /**
     * 207: 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<>();
        visited = new int[numCourses];
        for(int i = 0; i < numCourses ; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < prerequisites.length ; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for(int i = 0; i < numCourses && notCircle ; i++){
            if(visited[i] == 0){
                DFS(i);
            }
        }
        return notCircle;
    }

    private void DFS(int i){
        visited[i] = 1;
        for( int node : graph.get(i) ){
            if(visited[node] == 0){
                DFS(node);
                if(!notCircle) return;
            }else if (visited[node] == 1){
                notCircle = false;
                return;
            }
        }
        visited[i] = 2;
    }


    /**
     * 208: 实现Trie
     */
    class Trie {

        class Node{
            char val;
            List<Node> next;
            boolean isEnd;
            Node(char val){
                this.val = val;
                this.isEnd = false;
            }
        }

        Node head;

        public Trie() {
            this.head = new Node('0');
        }

        public void insert(String word) {
            char[] charWord = word.toCharArray();
            Node now = head;
            for(int i = 0; i < charWord.length; i++){
                boolean falg = false;
                //1 next 为 null
                if(now.next == null){
                    Node temp = new Node(charWord[i]);
                    if(i == charWord.length - 1){
                        temp.isEnd = true;
                    }
                    now = temp;
                    List<Node> children = new LinkedList<>();
                    children.add(temp);
                    now.next = children;
                }else{
                    //2 next不为null
                    for( Node once : now.next ){
                        if(once.val == charWord[i]){
                            if(i == charWord.length - 1){
                                once.isEnd = true;
                            }
                            now = once;
                            falg = true;
                            break;
                        }
                    }
                    //3 next 中没有当前 字符
                    if(!falg){
                        List<Node> nodes = now.next;
                        Node temp = new Node(charWord[i]);
                        if(i == charWord.length - 1){
                            temp.isEnd = true;
                        }
                        nodes.add(temp);
                        now = temp;
                    }
                }

            }
        }

        public boolean search(String word) {
            char[] charWord = word.toCharArray();
            Node now = head;
            for(int i = 0; i < charWord.length; i++){
                boolean isContained = false;
                if(now.next == null) return false;
                for(Node temp : now.next){
                    if(temp.val == charWord[i]){
                        if(i == charWord.length - 1){
                            if(!temp.isEnd){
                                return false;
                            }
                        }
                        isContained = true;
                        now = temp;
                        break;
                    }
                }
                if(!isContained) return isContained;
            }
            return true;
        }

        public boolean startsWith(String prefix) {
            char[] charWord = prefix.toCharArray();
            Node now = head;
            for(int i = 0; i < charWord.length; i++){
                boolean isContained = false;
                if(now.next == null) return false;
                for(Node temp : now.next){
                    if(temp.val == charWord[i]){
                        isContained = true;
                        now = temp;
                        break;
                    }
                }
                if(!isContained) return isContained;
            }
            return true;
        }
    }


    int[] useTimes;
    List<String> ret;
    String[] options = {"(",")"};

    /**
     * 22: 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        useTimes = new int[2];
        useTimes[0] = 1;
        ret = new ArrayList<>();
        backTrack(n,"(");
        return ret;
    }
    private void backTrack(int n,String str){
        if(useTimes[0] == n && useTimes[1] == n && isLegal(str)){
            ret.add(new String(str));
            return;
        }

        for(int i = 0 ; i < 2 ; i++){
            if(useTimes[i] == n) continue;
            str = str + options[i];
            useTimes[i] += 1;
            backTrack(n,str);
            useTimes[i] -= 1;
            str = str.substring(0,str.length()-1);
        }
    }

    private boolean isLegal(String str){
        Stack<String> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(stack.isEmpty()){
                stack.push(chars[i]+"");
            }else if(chars[i] == ')' && stack.peek().equals("(") ){
                stack.pop();
            }else{
                stack.push(chars[i]+"");
            }
        }
        return stack.isEmpty();
    }


    boolean flag = false;

    /**
     * 79: 单词搜索
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();

        for(int i = 0; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] == chars[0]){
                    boolean isVisited[][] = new boolean[board.length][board[0].length];
                    isVisited[i][j] = true;
                    backTrack(board,isVisited,chars,1,i,j);
                    if(flag) return flag;
                }
            }
        }
        return flag;
    }

    private void backTrack(char[][] board,boolean[][] isVisited,char[] word,int position,int x,int y){
        if(position == word.length){
            flag = true;
            return;
        }
        if(x+1 < board.length && board[x+1][y] == word[position] && !isVisited[x+1][y]){
            isVisited[x+1][y] = true;
            backTrack(board,isVisited,word,position+1,x+1,y);
            isVisited[x+1][y] = false;
            if(flag) return;
        }
        if(x-1 >= 0 && board[x-1][y] == word[position] && !isVisited[x-1][y]){
            isVisited[x-1][y] = true;
            backTrack(board,isVisited,word,position+1,x-1,y);
            isVisited[x-1][y] = false;
            if(flag) return;
        }
        if(y+1 < board[0].length && board[x][y+1] == word[position] && !isVisited[x][y+1]){
            isVisited[x][y+1] = true;
            backTrack(board,isVisited,word,position+1,x,y+1);
            isVisited[x][y+1] = false;
            if(flag) return;
        }
        if(y-1 >= 0 && board[x][y-1] == word[position] && !isVisited[x][y-1]){
            isVisited[x][y-1] = true;
            backTrack(board,isVisited,word,position+1,x,y-1);
            isVisited[x][y-1] = false;
            if(flag) return;
        }
    }


    /**
     * 35: 搜索插入位置
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) /2 ;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) high = mid-1;
            if(nums[mid] < target) low = mid+1;
        }
        return low;
    }


    /**
     * 74: 搜索二维矩阵
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length * matrix[0].length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int x = mid / matrix[0].length;
            int y = mid % matrix[0].length;

            if(matrix[x][y] == target) return true;
            if(matrix[x][y] < target) low = mid + 1;
            if(matrix[x][y] > target) high = mid - 1;
        }
        return false;
    }


    /**
     * 34: 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int position = findPosition(nums , target);
        if(position == -1) return new int[]{-1,-1};
        int[] res = {position,position};
        int i = 1;
        while(position-i>=0 && nums[position - i] == target){
            res[0] = position - i;
            i++;
        }
        i = 1;
        while(position+i < nums.length && nums[position + i] == target){
            res[1] = position + i;
            i++;
        }
        return res;
    }


    private int findPosition(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) /2 ;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) high = mid-1;
            if(nums[mid] < target) low = mid+1;
        }
        return -1;
    }


    /**
     * 33: 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int k = 0;
        for(int i = 1; i < nums.length ; i++){
            if(nums[i] < nums[i-1]){
                k = i;
                break;
            }
        }
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int rowMid = low + (high - low) /2 ;
            int mid = (rowMid + k) % nums.length;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) high = rowMid-1;
            if(nums[mid] < target) low = rowMid+1;
        }
        return -1;
    }


    /**
     * 4: 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int mid = 0;
        double ret = 0;
        if(totalLength % 2 == 0){
            mid = totalLength / 2;
            ret = findPosition(nums1,nums2,mid);
            ret = (ret + findPosition(nums1,nums2,mid+1)) /2;
        }else{
            mid = totalLength / 2 + 1;
            ret = findPosition(nums1,nums2,mid);
        }
        return ret;
    }

    private int findPosition(int[] nums1, int[] nums2,int coordinate){
        int i = 0;
        int j = 0;
        int count = 1;
        int ret;
        while(true){
            if(j == nums2.length){
                ret = nums1[i];
                i++;

            }else if(i == nums1.length){
                ret = nums2[j];
                j++;

            }
            else if(nums1[i] < nums2[j] ){
                ret = nums1[i];
                i++;
            }else {
                ret = nums2[j];
                j++;
            }
            if(count == coordinate) return ret;
            count++;
        }
    }

    /**
     * 155: 最小栈
     */
    class MinStack {
        // 用来存储差值
        private ArrayList<Long> stack;
        // 当前的最小值
        private long minVal;

        // 初始化构造器
        public MinStack() {
            stack = new ArrayList<>();
            minVal = Long.MAX_VALUE;
        }

        // 压入元素
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.add(0L);
                minVal = val;
            } else {
                long diff = val - minVal;
                stack.add(diff);
                if (diff < 0) {
                    minVal = val;
                }
            }
        }

        // 弹出元素
        public void pop() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            long diff = stack.remove(stack.size() - 1);
            if (diff < 0) {
                minVal -= diff;
            }
        }

        // 获取栈顶元素
        public int top() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            long diff = stack.get(stack.size() - 1);
            if (diff < 0) {
                return (int) minVal;
            } else {
                return (int) (minVal + diff);
            }
        }

        // 获取最小值
        public int getMin() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return (int) minVal;
        }
    }


    /**
     * 394: 字符串解码
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == ']'){
                String repeatORA = new String();
                while(stack.peek() != '['){
                    repeatORA = stack.pop() + repeatORA;
                }
                stack.pop();
                String num = new String();
                while(!stack.isEmpty() && stack.peek() <= 57 && stack.peek() >= 48){
                    num = stack.pop() + num;
                }
                int times = Integer.valueOf(num);
                String repeat = new String();
                while(times != 0){
                    repeat = repeat + repeatORA;
                    times--;
                }
                for(int j = 0 ; j < repeat.length() ; j++){
                    stack.push(repeat.charAt(j));
                }
            }else stack.push(s.charAt(i));

        }
        String ret = new String();
        while(!stack.isEmpty()){
            ret = stack.pop() + ret;
        }
        return ret;
    }


    /**
     * 739: 每日温度
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < temperatures.length ; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int temp = stack.pop();
                ret[temp] = i - temp;
            }
            stack.push(i);
        }
        return ret;
    }

    /**
     * 84: 柱状图中最大的矩形
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] minsStep = new int[heights.length];
        for(int i = 1 ; i < heights.length ; i++){
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int temp = stack.pop();
                minsStep[temp] = i - temp;
            }
            stack.push(i);
        }
        Stack<Integer> stackRight = new Stack<>();
        stackRight.push(heights.length - 1);
        int[] minsStepRight = new int[heights.length];
        for(int i = heights.length - 2 ; i >= 0 ; i--){
            while(!stackRight.isEmpty() && heights[i] < heights[stackRight.peek()]){
                int temp = stackRight.pop();
                minsStepRight[temp] = temp - i;
            }
            stackRight.push(i);
        }
        for(int i = 0; i < heights.length ; i++){
            int step = minsStep[i] == 0? heights.length-i : minsStep[i];
            int stepRight = minsStepRight[i] == 0 ? i+1 : minsStepRight[i];
            max = Math.max(max, heights[i]*(step+ stepRight-1));
        }
        return max;
    }


    /**
     * 295: 数据流的中位数
     */
    class MedianFinder {

        ArrayList<String> list = new ArrayList<>();

        PriorityQueue<Integer> A;
        PriorityQueue<Integer> B;

        public MedianFinder() {
            this.A = new PriorityQueue<>((a,b) -> (b-a));
            this.B = new PriorityQueue<>((a,b) -> (a-b));
        }

        public void addNum(int num) {
            if(A.size() == B.size()){
                if(A.size() == 0){
                    A.offer(num);
                }else if(num >= B.peek()){
                    B.offer(num);
                }else if(num <= A.peek()){
                    A.offer(num);
                }else A.offer(num);
            }else if (A.size() > B.size()){
                if(num >= A.peek()){
                    B.offer(num);
                }else {
                    A.offer(num);
                    B.offer(A.poll());
                }
            }else{
                if(num <= B.peek()){
                    A.offer(num);
                }else{
                    B.offer(num);
                    A.offer(B.poll());
                }
            }
        }

        public double findMedian() {
            if(A.size() == B.size()){
                return (A.peek() + B.peek()) / 2.0;
            }else if(A.size() > B.size()){
                return A.peek();
            }else return B.peek();
        }
    }


    /**
     * 152:乘积最大子数组
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        double[] dpMax = new double[nums.length];
        double[] dpMin = new double[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        double max = nums[0];
        for(int i = 1; i < nums.length ; i++){
            dpMax[i] = Math.max(dpMax[i-1]*nums[i],Math.max(nums[i],dpMin[i-1]*nums[i]));
            dpMin[i] = Math.min(dpMax[i-1]*nums[i],Math.min(nums[i],dpMin[i-1]*nums[i]));
            max = Math.max(max,dpMax[i]);
        }
        return (int)max;
    }

    public int longestValidParentheses(String s) {
        if(s.length() < 2) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for(int i = 1; i < s.length() ; i++){
            if(s.charAt(i) == '('){
                dp[i] = 0;
                continue;
            }
            if(i - dp[i-1] - 1 >= 0 && s.charAt( i - dp[i-1] - 1  ) == '('){
                dp[i] = dp[i-1] + 2;
                int j=i;
                while( j >= 0 && j - dp[j] >= 0 &&  dp[j - dp[j]] != 0){
                    dp[i] += dp[j - dp[j]];
                    j -= dp[j];
                }
            }else{ dp[i] = 0; }
            max = Math.max(max,dp[i]);
        }
        return max;
    }



    





}
