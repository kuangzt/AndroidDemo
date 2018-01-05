package com.example;

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] arr = new int[] {0,9,3,8,1,2,4,5};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i=0;i<arr.length-1&&flag;i++) {
            flag = false;
            for(int j=0;j<arr.length-1-i;j++) {
                if (arr[j]>arr[j+1]) {
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    flag = true;
                }
            }
            if (flag) {
                print(arr,i);
            }

        }
    }

    public static void print(int[] arr,int index) {
        System.out.printf("第%d次遍历",index);
        for (int i=0;i<arr.length;i++) {
            System.out.print(" "+arr[i]);
        }
        System.out.println();
    }
}
