/*
author：墨玄
date: 12-14-2018
this file shows the differences between segmentTree in  sum , xor max and min
*/

package algcollection.tree;

public class SegmentTree {
    int[] streeOfMax, streeOfMin, streeOfSum, streeOfXor;
    int size;

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
        streeOfXor[pos] = streeOfXor[pos * 2 + 1] ^ streeOfXor[pos * 2 + 2];
    }

    void constructTree(int[] array, int length) {
        this.size = length;
        int height = (int) Math.ceil(Math.log(length) / Math.log(2)); // height of stree
        int size = 2 * (int) Math.pow(2, height) - 1;
        mallocMemOfStree(size);
        // malloc memory for stree, construct the segmentTree
        buildUtil(array, 0, length - 1, 0);
    }

    int queryUtil(int left, int right, int qleft, int qright, int pos, int kind) { // query for segment Tree
        if (qleft <= left && qright >= right) {
            switch (kind) {
            case 0:
                return streeOfMax[pos];
            case 1:
                return streeOfMin[pos];
            case 2:
                return streeOfSum[pos];
            case 3:
                return streeOfXor[pos];
            default:
                System.out.println("error happen in query");
                return 0;
            }
        }
        if (qleft > right || qright < left) {
            switch (kind) {
            case 0:
                return Integer.MIN_VALUE;
            case 1:
                return Integer.MAX_VALUE;
            case 2:
            case 3:
                return 0;
            }
        }
        int mid = left + (right - left) / 2;
        int leftQuery = queryUtil(left, mid, qleft, qright, pos * 2 + 1, kind);
        int rightQuery = queryUtil(mid + 1, right, qleft, qright, pos * 2 + 2, kind);
        int result = 0;
        switch (kind) {
        case 0:
            result = Math.max(leftQuery, rightQuery);
            break;
        case 1:
            result = Math.min(leftQuery, rightQuery);
            break;
        case 2:
            result = leftQuery + rightQuery;
            break;
        case 3:
            result = leftQuery ^ rightQuery;
            break;
        }
        return result;
    }

    int queryTree(int qleft, int qright, int kind) {
        if (qleft > qright || qleft > size - 1 || qright < 0) {
            System.out.println("Invalid Input Query");
            return 0;
        }
        return queryUtil(0, size - 1, qleft, qright, 0, kind);
    }

    void updateUtil(int left, int right, int index, int oldvalue, int newvalue, int pos) {
        if (index < left || index > right)
            return;
        if (left == right) { // update should refresh value here
            streeOfMax[pos] = streeOfMin[pos] = streeOfSum[pos] = streeOfXor[pos] = newvalue;
            return;
        }
        streeOfMax[pos] = Math.max(streeOfMax[pos], newvalue);
        streeOfMin[pos] = Math.min(streeOfMin[pos], newvalue);
        streeOfSum[pos] += (newvalue - oldvalue);
        streeOfXor[pos] = streeOfXor[pos] ^ oldvalue ^ newvalue;

        if (left != right) {
            int mid = left + (right - left) / 2;
            updateUtil(left, mid, index, oldvalue, newvalue, pos * 2 + 1);
            updateUtil(mid + 1, right, index, oldvalue, newvalue, pos * 2 + 2);
        }
    }

    void updateTree(int index, int newValue) {
        if (index < 0 || index > size - 1) {
            System.out.println("Invalid Input In Update");
            return;
        }
        int oldValue = streeOfMax[index];
        // streeOfMax[index] = streeOfMin[index] = streeOfSum[index] = streeOfXor[index]
        // = newValue;
        updateUtil(0, size - 1, index, oldValue, newValue, 0);
    }

    public static void main(String[] args) {
        SegmentTree segTree = new SegmentTree();
        int[] TestArray = { 1, 2, 3, 4 };
        segTree.constructTree(TestArray, TestArray.length);
        segTree.printTrees();
        segTree.updateTree(1, 5);
        segTree.printTrees();

        // System.out.println(segTree.queryTree(1, 2, 1));

        System.out.println("run finished!");
    }

    void printTrees() {
        int length = streeOfMax.length;
        printfSingleTree(streeOfMax, length, "MaxArray");
        printfSingleTree(streeOfMin, length, "MinArray");
        printfSingleTree(streeOfSum, length, "SumArray");
        printfSingleTree(streeOfXor, length, "XorArray");
    }

    void printfSingleTree(int[] arr, int length, String arrname) {
        System.out.println(arrname + " :");
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}