package algcollection.sort;

public class QuickSort {
    int[] array;

    public QuickSort(int[] copy) {
        array = new int[copy.length];
        System.arraycopy(copy, 0, array, 0, array.length);
    }

    public void sort() {
        sort(0, array.length - 1);
    }

    public void sort(int left, int right) {
        if (left >= right) return;
        int divided = divideOfFirst(left, right);

        sort(left, divided - 1);

        sort(divided + 1, right);
    }


    private int divide(int left, int right) {
        int divided = array[left];
        int i = left, j = right;
        while (i < j) {
            while (array[j] > divided && i < j) j--;
            while (array[i] <= divided && i < j) i++;
            if (i < j) swap(i, j);
        }
        swap(left, j);
        return j;
    }

    private int divideOfIn(int left, int right) {
        int divided = array[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && array[j] > divided) j--;
            if (i < j) array[i++] = array[j];
            while (i < j && array[i] < divided) i++;
            if (i < j) array[j--] = array[i];
        }
        array[i] = divided;
        return i;
    }

    private int divideOfFirst(int left, int right) {
        int divided = array[left];
        int i = left, j = left + 1;
        while (j <= right) {
            if (array[j] < divided) {
                swap(++i, j);
            }
            j++;
        }
        swap(left, i);
        return i;
    }

    public void sortNotKnown(int left, int right) {
        if (left >= right) return;
        int divided = dividedNotKnown(left, right);

        sortNotKnown(left, divided - 1);

        sortNotKnown(divided, right);
    }

    private int dividedNotKnown(int left, int right) {
        int mid = (left + right) / 2;
        int pivot = array[mid];

        while (left <= right) {
            while (array[left] < pivot) {
                ++left;
            }
            while (pivot < array[right]) {
                --right;
            }
            if (left <= right) {
                swap(left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    private void swap(int n, int m) {
        int temp = array[n];
        array[n] = array[m];
        array[m] = temp;
    }

    public int[] getArray() {
        return array;
    }
}
