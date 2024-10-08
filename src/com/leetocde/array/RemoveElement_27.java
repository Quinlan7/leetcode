package com.leetocde.array;/**
 * @author zhf
 * @date 2023/2/26 9:13
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode27
 * @date 2023/2/26 9:13
 **/
public class RemoveElement_27 {

}


/**
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 链接：https://leetcode.cn/problems/remove-element
 */

/**
 * 思路比较简单：主要就是一个for循环遍历到和val相等的元素时，我们直接用数组尾部的元素
 * 来替换当前元素，但是有很多小细节需要处理。
 * 在用数组尾部元素进行替换时，我们要注意，数组尾部的元素我么也要对其进行判断：
 * 看尾部元素是否也等于val，如果等于我们就需要让我们的尾指针前移一位，然后进行判断前移后的指针是否也等于val，循环
 * 并且在这个循环的条件中还要判断，头指针是否大于了尾指针，大于的话也要跳出去
 * @Time-complexity 虽然看似有两重的for循环，但是一个从头开始，一个从尾开始，且俩个循环相遇即退出循环，
 * 所以其实时间复杂度也是O(N)
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        int tailPointer = 0;
        //头指针寻找等于cal的元素位置
        for (int headPointer = 0; headPointer < nums.length- tailPointer; headPointer++) {
            if(nums[headPointer] == val){
                //尾指针寻找用于替换头部等于val元素的值
                for (; nums[nums.length-1- tailPointer]==val && headPointer < nums.length- tailPointer -1; tailPointer++) {
                }
                nums[headPointer]=nums[nums.length-1- tailPointer];
                tailPointer++;
            }
        }
        return nums.length- tailPointer;
    }
}


/**
 * 这是另一种解法，使用的是快慢指针，都从头部开始遍历，只不过是没有找到等于val的时候，就用快指针
 * 为慢指针赋值，找到的话就跳过，其实是把整个数组重新赋值的，只不过只有一个数组所以使用了两个指针。
 * @compare 我觉得不如我的解法，这个解法的赋值次数过多！（基于数组中的等于val的值比较少的话）
 */
//class Solution {
//    public int removeElement(int[] nums, int val) {
//
//        // 快慢指针
//        int fastIndex = 0;
//        int slowIndex;
//        for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
//            if (nums[fastIndex] != val) {
//                nums[slowIndex] = nums[fastIndex];
//                slowIndex++;
//            }
//        }
//        return slowIndex;
//
//    }
//}