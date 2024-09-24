package com.interview;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class base {

    // 初始化信号量，只有A的信号量初始为1，表示T1可以首先执行，其他为0，表示T2和T3需要等待
    private static final Semaphore semaphoreA = new Semaphore(1);
    private static final Semaphore semaphoreB = new Semaphore(0);
    private static final Semaphore semaphoreC = new Semaphore(0);

    public static void main1(String[] args) {
        // 线程T1输出'A'
        Thread T1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {  // 循环三次
                try {
                    semaphoreA.acquire();  // 获取信号量，T1开始执行
                    System.out.print("A");
                    semaphoreB.release();  // 释放T2的信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 线程T2输出'B'
        Thread T2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {  // 循环三次
                try {
                    semaphoreB.acquire();  // 获取信号量，T2开始执行
                    System.out.print("B");
                    semaphoreC.release();  // 释放T3的信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 线程T3输出'C'
        Thread T3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {  // 循环三次
                try {
                    semaphoreC.acquire();  // 获取信号量，T3开始执行
                    System.out.print("C");
                    semaphoreA.release();  // 释放T1的信号量，让下一轮A执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        T1.start();
        T2.start();
        T3.start();
    }
    public static void main2(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(2);
        q.add(5);
        q.add(32);
        q.add(8);
        q.add(1);
        q.add(90);
        q.add(55);
        System.out.println(q.contains(5));
        q.remove(5);
        System.out.println(q.contains(5));
        while(!q.isEmpty()){
            System.out.println(q.poll());
        }

    }

    private static void quickSort(int[] nums , int left , int right){
        if(left < right){
            int pivot = partition(nums,left,right);
            quickSort(nums,left,pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int k = nums[left];
        while(left < right){
            while(left < right && nums[right] >= k) right--;
            nums[left] = nums[right];
            while(left < right && nums[left] <= k) left++;
            nums[right] = nums[left];
        }
        nums[left] = k;
        return left;
    }

    @Test
    private static int kLargest(){

        return 1;
    }


    public static void quickSort1(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition1(arr, low, high);
            quickSort1(arr, low, pivotIndex - 1);
            quickSort1(arr, pivotIndex + 1, high);
        }
    }

    private static int partition1(int[] arr, int low, int high) {
        // 使用三数中值分割法选择枢轴
        int medianIndex = medianOfThree(arr, low, high);
        swap(arr, medianIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int medianOfThree(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        if (arr[low] > arr[high]) {
            swap(arr, low, high);
        }
        if (arr[mid] > arr[high]) {
            swap(arr, mid, high);
        }
        if (arr[mid] > arr[low]) {
            swap(arr, mid, low);
        }
        // 此时arr[low] <= arr[mid] <= arr[high]
        return low; // 返回中值作为枢轴
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, 20, 10));
        products.add(new Product(2, 40, 8));
        products.add(new Product(3, 50, 12));

        int budget = 100;
        int maxPoints = findMaxPoints(budget, products);
        System.out.println("Maximum shopping points achievable with a budget of " + budget + " is: " + maxPoints);
    }





    public static int findMaxPoints(int budget, List<Product> products) {
        int n = products.size();
        int[][] dp = new int[n + 1][budget + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= budget; j++) {
                if (products.get(i - 1).price > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], products.get(i - 1).shoppingPoints
                            + dp[i - 1][(int) (j - products.get(i - 1).price)]);
                }
            }
        }

        return dp[n][budget];
    }
    static class Product {
        int goodId;
        double price;
        int shoppingPoints;

        public Product(int goodId, double price, int shoppingPoints) {
            this.goodId = goodId;
            this.price = price;
            this.shoppingPoints = shoppingPoints;
        }
    }


}
