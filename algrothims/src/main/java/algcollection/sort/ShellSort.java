package algcollection.sort;

public class ShellSort {

    private int[] array;
    private int divCount = 3;

    public ShellSort(int[] copy) {
        array = new int[copy.length];
        System.arraycopy(copy, 0, array, 0, copy.length);
    }

    public int[] sort() {
        int distance = 1;
        while (distance * divCount < array.length) distance *= divCount;

        while (distance != 0) {
            for (int i = distance; i < array.length; i++) {
                for (int j = i; j >= distance && array[j] < array[j - distance]; j -= distance) {
                    swap(j, j - distance);
                }
            }

            distance /= divCount;
        }

        return array;
    }

    private void swap(int n, int m) {
        int temp = array[n];
        array[n] = array[m];
        array[m] = temp;
    }
}
