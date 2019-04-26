package algcollection.sort;

public class QuickSort {
    int[] array;

    public QuickSort(int[] copy) {
        array = new int[array.length];
        System.arraycopy(copy, 0, array, 0, array.length);
    }

    public void sort() {
        sort(0, array.length - 1);
    }

    public void sort(int left, int right) {
        if (left >= right) return;
        int divided = array[left];
        int i = left, j = right;
        while (i < j) {

        }
    }
}
