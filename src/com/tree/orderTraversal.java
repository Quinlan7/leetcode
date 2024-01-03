package com.tree;/**
 * @author zhf
 * @date 2024/1/3 14:30
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

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
}
