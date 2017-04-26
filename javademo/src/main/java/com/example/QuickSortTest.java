package com.example;

import java.util.Arrays;


public class QuickSortTest {
    public static void  main(String[] args){
        System.out.println("main");
        int[] data = new int[]{10,5,8,6,3,89,66,32,0,0,0,0,0,0,89,88};
        quickSort(data,0,data.length-1);
        System.out.println("sort"+ Arrays.toString(data));
    }

    public static void quickSort(int[] data,int start,int end){
        if(start<end){
            int privotIndex = quickSortPartition(data,start,end);
            quickSort(data,start,privotIndex-1);
            quickSort(data,privotIndex+1,end);
        }
    }

    private static int quickSortPartition(int[] data,int start,int end){
        int low = start+1;
        int high = end;
        int privot = data[start];
        while (low<high){
            while(low<=high&&data[low]<=privot){
                low++;
            }
            while(low<=high&&data[high]>privot){
                high--;
            }
            if(low<high){
                int temp = data[low];
                data[low] = data[high];
                data[high] = temp;
            }
        }
        while (high>start&&data[high]>=privot){
            high--;
        }
        if(data[high]<privot){
            data[start] = data[high];
            data[high] = privot;
            return high;
        } else {
            return start;
        }
    }
}
