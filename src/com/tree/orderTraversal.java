package com.tree;

import com.stacksandqueues.EvalRPN;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：二叉树的遍历
 * @date 2024/1/3 14:30
 **/
@SuppressWarnings("all")
public class orderTraversal {

    /**
     * 前序遍历:根左右
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        ret.add(root.val);
        ret.addAll(preorderTraversal(root.left));
        ret.addAll(preorderTraversal(root.right));
        return ret;
    }

    /**
     * 后序遍历:左右根
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        ret.addAll(postorderTraversal(root.left));
        ret.addAll(postorderTraversal(root.right));
        ret.add(root.val);
        return ret;
    }

    /**
     * 中序遍历:左根右
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        ret.addAll(inorderTraversal(root.left));
        ret.add(root.val);
        ret.addAll(inorderTraversal(root.right));
        return ret;
    }

    /**
     * 层序遍历(递归方式)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_1(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        recursiveHierarchicalTraversal(root,ret,0);
        return ret;
    }

    private void recursiveHierarchicalTraversal(TreeNode root, List<List<Integer>> ret, int deep) {
        if(root == null) return;
        deep++;
        if(ret.size() < deep){
            List<Integer> temp = new ArrayList<>();
            ret.add(temp);
        }
        ret.get(deep-1).add(root.val);
        recursiveHierarchicalTraversal(root.left,ret,deep);
        recursiveHierarchicalTraversal(root.right,ret,deep);
    }

    /**
     * 层序遍历(队列方式)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_2(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            List<Integer> temp = new ArrayList<>();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
            ret.add(temp);
        }
        return ret;
    }


    /**
     * 后层序遍历(队列方式)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            List<Integer> temp = new ArrayList<>();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
            ret.add(temp);
        }
        Collections.reverse(ret);
        return ret;
    }


    /**
     * 二叉树右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            while (len != 0){
                if(len==1) ret.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        return ret;
    }


    /**
     * 二叉树层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return null;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            List<Integer> temp = new ArrayList<>();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
            ret.add(temp);
        }
        List<Double> rett = new ArrayList<>();
        rett = ret.stream()
                .mapToDouble(subList -> subList.stream().mapToInt(Integer::intValue).average().orElse(0.0))
                .boxed()//原始类转化为包装类
                .collect(Collectors.toList());
        return rett;
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * N叉树 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            List<Integer> temp = new ArrayList<>();
            while (len != 0){
                Node cur = que.poll();
                temp.add(cur.val);
                if(cur.children != null){
                    cur.children.stream().forEach(node -> que.offer(node));
                }
                len--;
            }
            ret.add(temp);
        }
        return ret;

    }

    /**
     * 在树的每层中寻找最大值
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) return ret;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            int max = que.peek().val;
            while(len!=0){
                TreeNode temp = que.poll();
                max = max < temp.val ? temp.val:max;
                if(temp.left!=null) que.offer(temp.left);
                if(temp.right!=null) que.offer(temp.right);
                len -= 1;
            }
            ret.add(max);
        }

        return ret;
    }

    class Node116 {
        public int val;
        public Node116 left;
        public Node116 right;
        public Node116 next;

        public Node116() {}

        public Node116(int _val) {
            val = _val;
        }

        public Node116(int _val, Node116 _left, Node116 _right, Node116 _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /**
     * 116、117
     * @param root
     * @return
     */
    public Node116 connect(Node116 root) {
        if (root == null) return root;
        Queue<Node116> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            while (len != 0){
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                Node116 temp = que.poll();
                if (len > 1) {
                    temp.next = que.peek();
                }
                len--;
            }
        }
        return root;
    }


    /**
     * 104:深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        int deep = 0;
        if (root == null) return deep;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            deep++;
            while (len != 0){
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        return deep;
    }


    /**
     * 111:最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        int deep = 0;
        if (root == null) return deep;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            deep++;
            while (len != 0){
                if(que.peek().left == null && que.peek().right==null) return deep;
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        return deep;
    }


    /**
     * 226：翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            while(len != 0){
                TreeNode temp = que.peek().left;
                que.peek().left = que.peek().right;
                que.peek().right = temp;
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        return root;
    }


    /**
     * 101:对称二叉树
     * 左孩子等于另一棵树的右孩子
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root,root);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return check(left.left,right.right) && check(left.right,right.left);
    }


    /**
     * 222：完全二叉树结点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int ret = 0;
        if (root == null) return ret;
        ret++;
        ret = ret + countNodes(root.left);
        ret = ret + countNodes(root.right);
        return ret;
    }


    /**
     * 110:平衡二叉树
     * 重点：平衡二叉树的性质：一个二叉树的每个结点的左右两个子树的高度差绝对值不超过1
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        return heightDifference(root.left,root.right);
    }

    private boolean heightDifference(TreeNode left, TreeNode right) {
        int deepL = maxDepth(left);
        int deepR = maxDepth(right);
        if(Math.abs(deepL-deepR) < 2 && left == null && right == null) return true;
        if(Math.abs(deepL-deepR) < 2 && left == null) return true  && heightDifference(right.left,right.right);
        if(Math.abs(deepL-deepR) < 2 && right == null) return true && heightDifference(left.left,left.right);
        if(Math.abs(deepL-deepR) < 2) return true && heightDifference(left.left,left.right) && heightDifference(right.left,right.right);
        return false;
    }


    /**
     * 257:二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(root == null) return ret;
        paths(root,String.valueOf(root.val),ret);
        return ret;
    }

    private void paths(TreeNode root, String path, List<String> paths) {
        if(root.left == null && root.right == null){
            paths.add(path);
        }
        if(root.left!=null){
            paths(root.left,path+"->"+root.left.val,paths);
        }
        if(root.right!=null){
            paths(root.right,path+"->"+root.right.val,paths);
        }
    }


    /**
     * 100:相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q != null) return false;
        if(q == null && p != null) return false;
        if(q == null && p == null) return true;
        if(p.val != q.val) return false;
        return p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }


    /**
     * 404:左叶子之和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return sumOfLeftLeaves(root.right);
        if(root.left.left == null && root.left.right == null) {
            return root.left.val+sumOfLeftLeaves(root.right);
        }else return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }


    /**
     * 513:找树左下角的值
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            Integer len = que.size();
            List<Integer> temp = new ArrayList<>();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
            ret.add(temp);
        }
        List<Integer> temp = ret.get(ret.size()-1);
        return temp.get(0);
    }


    /**
     * 112:路经总和
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        List<Integer> sums = new ArrayList<>();
        Integer sum = 0;
        pathsum(root,sums,sum);
        for (int temp:
             sums) {
            if (temp == targetSum) return true;
        }
        return false;
    }

    private void pathsum(TreeNode root, List<Integer> sums, Integer sum) {
        if(root == null) return;
        if(root.left == null && root.right == null){
            sum += root.val;
            sums.add(sum);
            return;
        }
        sum += root.val;
        pathsum(root.left,sums,sum);
        pathsum(root.right,sums,sum);
    }


    /**
     * 106:根据中序和后序构造二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode treeNode = new TreeNode();
        if(inorder.length == 0 || postorder.length == 0) return treeNode;
        buildTree(treeNode,inorder,postorder);
        return treeNode;
    }

    private void buildTree(TreeNode treeNode, int[] inorder, int[] postorder) {
        if(inorder.length == 0 || postorder.length == 0) return;
        treeNode.val = postorder[postorder.length - 1];
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i] == treeNode.val){
                index = i;
                break;
            }
        }
        // 使用Arrays.copyOfRange分割数组
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);

        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, index);
        int[] rightPostorder = Arrays.copyOfRange(postorder, index, inorder.length - 1);

        if(leftInorder.length !=0 ){
            TreeNode nodeLeft = new TreeNode();
            treeNode.left = nodeLeft;
            buildTree(treeNode.left,leftInorder,leftPostorder);
        }
        if(rightInorder.length !=0 ){
            TreeNode nodeRight = new TreeNode();
            treeNode.right = nodeRight;
            buildTree(treeNode.right,rightInorder,rightPostorder);
        }
    }


    /**
     * 654:最大二叉树
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode treeNode = new TreeNode();
        if(nums.length == 0 ) return treeNode;
        constructMaximumBinaryTree(treeNode,nums);
        return treeNode;
    }

    private void constructMaximumBinaryTree(TreeNode treeNode, int[] nums) {
        int max = 0,index = 0;
        for (int i = 0; i < nums.length; i++) {
            max = nums[i]>max? nums[i] : max;
        }
        treeNode.val = max;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == treeNode.val){
                index = i;
                break;
            }
        }
        int[] left = Arrays.copyOfRange(nums, 0, index);
        int[] right = Arrays.copyOfRange(nums, index + 1, nums.length);
        if(left.length !=0 ){
            TreeNode nodeLeft = new TreeNode();
            treeNode.left = nodeLeft;
            constructMaximumBinaryTree(treeNode.left,left);
        }
        if(right.length !=0 ){
            TreeNode nodeRight = new TreeNode();
            treeNode.right = nodeRight;
            constructMaximumBinaryTree(treeNode.right,right);
        }
    }


    /**
     * 617:合并二叉树
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return null;
        TreeNode treeNode = new TreeNode();
        treeNode.val = (root1 != null? root1.val:0)+(root2 != null? root2.val : 0);
        treeNode.left=mergeTrees(root1!=null?root1.left:null,root2!=null?root2.left:null);
        treeNode.right=mergeTrees(root1!=null?root1.right:null,root2!=null?root2.right:null);
        return treeNode;
    }


    /**
     * 700:二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;
        if(val > root.val) return searchBST(root.right,val);
        if(val < root.val) return searchBST(root.left,val);
        return null;
    }


    /**
     * 98:验证二叉搜索树
     * @param root
     * @return
     */
    @Test
    public void test1(){
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(0);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode.left = treeNode1;
        treeNode.right = treeNode4;
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode4.left = treeNode5;
        treeNode4.right = treeNode6;

        boolean ret = isValidBST(treeNode);
        System.out.println(ret);
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        int temp = root.val;
        if (ValidBSTleft(root.left,temp) && ValidBSTright(root.right,temp)) {
            return isValidBST(root.left) && isValidBST(root.right);
        }
        return false;
    }

    private boolean ValidBSTright(TreeNode right, int temp) {
        if(right == null) return true;
        if(right.val > temp) return true&&ValidBSTright(right.left,temp)&&ValidBSTright(right.right,temp);
        return false;
    }

    private boolean ValidBSTleft(TreeNode left, int temp) {
        if(left == null) return true;
        if(left.val < temp) return true&&ValidBSTleft(left.left,temp)&&ValidBSTleft(left.right,temp);
        return false;
    }

    /**
     * 530:二叉搜索树的最小绝对差
     * @param root
     * @return
     */
    @Test
    public void test2(){
        TreeNode treeNode = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode.left = treeNode2;
        treeNode.right = treeNode6;
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;


        int ret = getMinimumDifference(treeNode);
        System.out.println(ret);
    }
    public int getMinimumDifference(TreeNode root) {
        int leftMin = 10001;
        int rightMin = 10001;
        if (root.left != null) {
            TreeNode max = root.left;
            while(max.right != null){
                max = max.right;
            }
            leftMin = Math.min(Math.abs(max.val-root.val),getMinimumDifference(root.left));
        }
        if (root.right != null) {
            TreeNode max = root.right;
            while(max.left != null){
                max = max.left;
            }
            rightMin = Math.min(Math.abs(max.val-root.val),getMinimumDifference(root.right));
        }
        return Math.min(leftMin,rightMin);
    }


    /**
     * 501:二叉搜索树中的众数
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        List<Integer> temp = new ArrayList<>();
        while(!que.isEmpty()){
            Integer len = que.size();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        Map<Integer,Integer> frequencyMap = new HashMap<>();
        for (Integer val:
             temp) {
            frequencyMap.put(val,frequencyMap.getOrDefault(val,0)+1);
        }
        // 找到出现次数最多的数字
        int maxFrequency = 0;
        for (int frequency : frequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }
        List<Integer> retList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> map:
             frequencyMap.entrySet()) {
            if(map.getValue() == maxFrequency) retList.add(map.getKey());
        }
        int[] ret = retList.stream().mapToInt(Integer::intValue).toArray();
        return ret;
    }


    /**
     * 236:二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        List<Integer> temp = new ArrayList<>();
        while(!que.isEmpty()){
            Integer len = que.size();
            while (len != 0){
                temp.add(que.peek().val);
                if(que.peek().left!=null) que.offer(que.peek().left);
                if(que.peek().right!=null) que.offer(que.peek().right);
                que.poll();
                len--;
            }
        }
        if(temp.contains(p.val)&&temp.contains(q.val)){
            TreeNode left = lowestCommonAncestor(root.left,p,q);
            TreeNode right = lowestCommonAncestor(root.right,p,q);
            if(left == null && right == null){
                return root;
            }
            return left==null? right : left;

        }
        return null;

    }

    //太强了
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }



}
