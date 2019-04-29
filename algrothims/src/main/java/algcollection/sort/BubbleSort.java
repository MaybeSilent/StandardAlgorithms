package algcollection.sort;

public class BubbleSort {
    private int[] array;

    public BubbleSort(int[] copy) {
        array = new int[copy.length];
        System.arraycopy(copy, 0, array, 0, copy.length);
    }

    public int[] sort() {
        for (int i = 0; i < array.length; i++) {
            int length = array.length - i;
            for (int j = 1; j < length; j++) {
                if (array[j - 1] > array[j]) {
                    swap(j - 1, j);
                }
            }
        }
        return array;
    }

    private void swap(int n, int m) {
        int temp = array[n];
        array[n] = array[m];
        array[m] = temp;
    }
}
