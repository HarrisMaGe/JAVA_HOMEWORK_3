package Matching;

import Database.OpenDB;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by 冬至节 on 2016/11/12.
 */
public class Score {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/time?useSSL=false";


    //源自网上的最坏情况为线性时间的选择算法,输出随机排列的数组里面第k大的值
    private static int select(int[] a, int law, int high, int k) {
        if (high - law < 5) {
            insertSort(a, law, high);
            return a[law + k - 1];
        }
        int teams = (high - law + 5) / 5;
        for (int i = 0; i < teams; i++) {
            // 第一步：将输入数组的n个元素划分为n/5组，每组5个元素，且至多只有一个组由剩下的n mod5个元素组成
            int left = law + i * 5;
            int right = (law + i * 5 + 4) > high ? high : law + i * 5 + 4;
            int mid = (left + right) / 2;
            // 第二步：寻找(n+4)/5个组中每一组的中位数。首先对每组中的元素（至多为5个）进行插入排序，然后从排序过的序列中选出中位数
            insertSort(a, left, right);
            swap(a, law + i, mid);// 将中位数置前
        }
        /* 第三步：对第二步中找出的(n+4)/5个中位数，递归调用select以找出其中位数x */
        int pivot = select(a, law, law + teams - 1, (teams + 1) / 2);
        /* 第四步：利用修改过的partition过程，按中位数的中位数x对输入数组进行划分 */
        int pos = partition(a, law, high, pivot);
        /* 第五步：判断pos位置是否为要找的数，若不是则在低区或者高区递归select */
        int leftNum = pos - law;
        if (k == leftNum + 1)
            return a[pos];
        else if (k <= leftNum)
            return select(a, law, pos - 1, k);
        else
            return select(a, pos + 1, high, k - leftNum - 1);
    }

    private static int partition(int[] a, int law, int high, int pivot) {
        int index = law;
        for (int i = law; i <= high; i++) {
            if (a[i] == pivot) {
                index = i;
                break;
            }
        }/* 找到枢纽的位置 */
        swap(a, index, high);
        int i = law - 1;
        for (int j = law; j < high; j++) {
            if (a[j] <= pivot) {
                swap(a, j, ++i);
            }
        }
        swap(a, high, ++i);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /* 插入排序 */
    private static void insertSort(int[] a, int law, int high) {
        for (int i = law + 1; i <= high; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= law && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public int ScoreSong(String song_id,int[][] fitSongInfo,int width){
        int all=0;
        int avil=0;
        for (int i=0;i<width;i++){
            if(fitSongInfo[i][2]>=8){
                avil=avil+fitSongInfo[i][2];
            }
            all =all+fitSongInfo[i][2];
        }
        int score=avil/all *100;
        return score;
    }

    public static int[][] getHighest(ArrayList<int[]> arr){
        Comparator<int[]> comparator = new Comparator<int[]>(){
            public int compare(int[] i1,int[] i2) {
                //先排年龄
                if(i1[1]>=i2[1]){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        };
        Collections.sort(arr,comparator);
        int highest[][] = new int[5][2];
        for(int i=0;i<5;i++){
            highest[i][0]=arr.get(i)[0];
            highest[i][1]=arr.get(i)[1];
        }
        return highest;
    }
}
