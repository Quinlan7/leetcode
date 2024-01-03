package com.tree;/**
 * @author zhf
 * @date 2024/1/3 14:37
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：二叉树
 * @date 2024/1/3 14:37
 **/
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
