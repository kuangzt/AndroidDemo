package com.example;

import java.util.Arrays;

public class HeapSortTest {
    public static void  main(String[] args){
        System.out.print("main");
        int[] data = new int[]{10,5,8,6,3,89,66,32,0,0,0,0,0,0,89,88};
        heapSort(data);
        System.out.print("sort"+ Arrays.toString(data));
    }

    public static void heapSort(int[] data){
        int length = data.length;
        for(int i=length/2-1;i>=0;i--){
            adustHeap(data,i,length);
        }

        for(int i=0;i<length;i++){
            swap(data,0,length-i-1);
            adustHeap(data,0,length-i-1);
        }
    }

    private static void swap(int[] data,int index1,int index2){
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    private static void adustHeap(int[] data,int index,int n){
        int temp = data[index];
        int childIndex = 0;
        while( index*2+1<n ){
            childIndex = index*2+1;
            if(childIndex!=n-1&&data[childIndex]<data[childIndex+1]){
                childIndex++;
            }
            if(temp>data[childIndex]){
                break;
            }else {
                data[index] = data[childIndex];
                index = childIndex;
            }
        }
        data[index] = temp;
    }
}
