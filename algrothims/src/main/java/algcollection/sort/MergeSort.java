package algcollection.sort;

public class MergeSort {

    int[] array;

    public MergeSort(int[] copy) {
        array = new int[copy.length];
        System.arraycopy(copy, 0, array, 0, copy.length);
    }

    public int[] sort() {
        MergeSort(0, array.length - 1);
        return array;
    }

    private void MergeSort(int left, int right) {
        if (right < left + 1) return;
        int mid = (right - left) / 2 + left;
        MergeSort(left, mid);
        MergeSort(mid + 1, right);
        Merge(left, mid + 1, right);
    }

    private void Merge(int left, int mid, int right) {
        int[] copy = new int[right - left + 1];
        int length = right - left;
        int startOfLeft = left;
        int startOfRight = mid;
        for (int i = 0; i <= length; i++) {
            if (startOfLeft < mid && startOfRight <= right) {
                copy[i] = array[startOfLeft] < array[startOfRight] ? array[startOfLeft++] : array[startOfRight++];
            } else if (startOfLeft < mid) {
                copy[i] = array[startOfLeft++];
            } else {
                copy[i] = array[startOfRight++];
            }
        }

        System.arraycopy(copy, 0, array, left, length + 1);
    }
}
