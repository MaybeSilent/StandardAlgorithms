/*
author：墨玄
date: 12-14-2018
*/

package algcollection;

public class SegmentTree {
    int[] streeOfMax, streeOfMin, streeOfSum, streeOfXor;

    void mallocMemOfStree(int size) {
        streeOfMax = new int[size];
        streeOfMin = new int[size];
        streeOfSum = new int[size];
        streeOfXor = new int[size];
    }

    void buildUtil(int[] arr, int left, int right, int pos) {
        if (left == right) {
            streeOfXor[pos] = streeOfSum[pos] = streeOfMin[pos] = streeOfMax[pos] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildUtil(arr, left, mid, pos * 2 + 1);
        buildUtil(arr, mid + 1, right, pos * 2 + 2);

        streeOfMax[pos] = Math.max(streeOfMax[pos * 2 + 1], streeOfMax[pos * 2 + 2]);
        streeOfMin[pos] = Math.min(streeOfMin[pos * 2 + 1], streeOfMax[pos * 2 + 2]);
        streeOfSum[pos] = streeOfSum[pos * 2 + 1] + streeOfSum[pos * 2 + 2];
        streeOfXor[pos] = streeOfXor[pos * 2 + 1] + streeOfXor[pos * 2 + 2];
    }

    void constructTree(int[] array, int length) {
        int height = (int) Math.ceil(Math.log(length) / Math.log(2)); // height of stree
        int size = 2 * (int) Math.pow(2, height) - 1;
        mallocMemOfStree(size);
        // malloc memory for stree, construct the segmentTree
        buildUtil(array, 0, length - 1, 0);
    }

    void queryUtil(){ //query for segment Tree
        
    }

    public static void main(String[] args) {
        
    }
}