package algcollection.sort;

public class HeapSort {

    //利用数组来表示相应的堆
    private int[] array;
    private final int initSize = 16;

    public HeapSort() {
        mallocSize(initSize);
    }

    public HeapSort(int[] inarray) {
        array = new int[inarray.length];
        System.arraycopy(inarray, 0, array, 0, inarray.length);
    }

    public HeapSort(int capacity) {
        mallocSize(capacity);
    }

    /**
     * 最后得到的结果为从大到小的数组
     * @return
     */
    public int[] sort() {
        int sizeOfHeap = array.length;
        /**
         * 值为相应内容-2,-1影响也不大
         */
        for (int k = (array.length - 2) / 2; k >= 0; k--) {
            down(k, sizeOfHeap);
        }
        /**
         * 每次交换了之后，都需要再次平衡
         */
        for (int i = 0; i < array.length; i ++){
            swapNoSpace(0, --sizeOfHeap);
            down(0, sizeOfHeap);
        }
        return array;
    }

    /**
     * 将数组中第k个值进行调整，使得堆的顺序不混乱
     * 此法为构建小顶堆
     * 构建大顶堆需要 ----------》 每次与字节点中最大且比自己大的元素进行交换
     *
     * @param k
     */
    private void down(int k, int sizeOfHeap) {
        while (k * 2 + 1 < sizeOfHeap) {
            int nodeAfter = k * 2 + 1;
            if (nodeAfter + 1 < sizeOfHeap && array[nodeAfter] > array[nodeAfter + 1]) {
                nodeAfter = nodeAfter + 1;
            }

            if (array[nodeAfter] >= array[k]) break;

            swapNoSpace(nodeAfter, k);

            k = nodeAfter;
        }
    }


    /**
     * 上浮操作，可用于往堆中插入元素之时
     *
     * @param k
     */
    private void up(int k) {
        while ((k - 1) / 2 >= 0) {
            int nodeUp = (k - 1) / 2;
            if (array[nodeUp] < array[k]) {
                break;
            } else {
                swapNoSpace(nodeUp, k);
            }
            k = nodeUp;
        }
    }

    private void swapNoSpace(int n, int m) {
        if (n == m) return ;
        array[n] ^= array[m];
        array[m] ^= array[n];
        array[n] ^= array[m];
    }

    private void swapSpace(int n, int m) {
        int copy = array[n];
        array[n] = array[m];
        array[m] = copy;
    }


    private void mallocSize(int capacity) {
        if (array != null) {
            // 如果新申请的容量小于原有的容量，则直接返回
            if (capacity < array.length) return;
            if (capacity < initSize) capacity = initSize;

            int[] ans = new int[capacity];
            System.arraycopy(array, 0, ans, 0, array.length);
            array = ans;
        } else {
            array = new int[capacity];
        }
    }


}
