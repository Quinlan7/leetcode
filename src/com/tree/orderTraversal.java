package com.tree;/**
 * @author zhf
 * @date 2024/1/3 14:30
 * @version 1.0
 */
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：二叉树的遍历
 * @date 2024/1/3 14:30
 **/
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


}
