package com.interview;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class dajiang {
    public static void main(String[] args) {
        int[] nums = new int[]{3,4,5,2};
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = nums.length-1; i >= 0 ; i--) {
            set.add(nums[i]);
            nums[i] = Optional.ofNullable(set.higher(nums[i])).orElse(-1);
        }
        System.out.println(Arrays.toString(nums));
    }




}
