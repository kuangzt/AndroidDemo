package com.example;

public class StringDemo {
    public static void main(String[] args){
        reverse2(new char[]{'9','1','2','3','a','e'});
    }

    public static void reverse(char[] a){
        int length = a.length;
        for(int i=0;i<length;i++){
            char first = a[0];
            for(int j=0;j<length-1-i;j++){
                a[j] = a[j+1];
            }
            a[length-i-1] = first;
        }
        System.out.print(a);
    }

    public static void reverse2(char[] a){
        int length = a.length;
        int mid = length/2;
        for(int i=0;i<mid;i++){
            swap(a,i,length-i-1);
        }
        System.out.print(a);
    }

    public static void swap(char[] a,int i,int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
