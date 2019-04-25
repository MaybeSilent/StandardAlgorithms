package algcollection.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

public class SortTest {

    @Test
    public void testSort() {
        for (int testNum = 0; testNum < 100; testNum++) {
            int[] arr = new int[5];
            fillRandom(arr);

            HeapSort heapSort = new HeapSort(arr);

            int[] ans = heapSort.sort();
            Arrays.sort(arr);

            reverse(arr);

            for (int i = 0; i < arr.length; i++) {
                Assertions.assertArrayEquals(arr , ans);
            }

            System.out.println("TESTCASE:"+(testNum+1)+"✅") ;
        }


    }


    private void fillRandom(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
    }

    private void reverse(int[] arr) {
        int length = arr.length / 2;
        for (int i = 0; i < length; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
