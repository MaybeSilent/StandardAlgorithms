package algcollection.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SegmentTreeTest {
    static SegmentTree testTree;

    @BeforeAll
    static void setup() {
        int[] arr = { 1, 2, 3, 4 };
        testTree = new SegmentTree();
        testTree.constructTree(arr, arr.length);
    }

    @BeforeEach
    void beforefunc() {
        System.out.println("=============T E S T==============");
    }

    @Test
    void testQuery() {
        int result = testTree.queryTree(1, 2, 0);
        Assertions.assertEquals(result, 3);
        result = testTree.queryTree(1, 2, 1);
        Assertions.assertEquals(result, 2);
        result = testTree.queryTree(1, 2, 2);
        Assertions.assertEquals(result, 5);
        result = testTree.queryTree(1, 2, 3);
        Assertions.assertEquals(result, 2 ^ 3);
    }

    @Test
    void testUpdate() {
        testTree.updateTree(1, 5);

        int result = testTree.queryTree(1, 2, 0);
        Assertions.assertEquals(result, 5);
        result = testTree.queryTree(1, 2, 1);
        Assertions.assertEquals(result, 3);
        result = testTree.queryTree(1, 2, 2);
        Assertions.assertEquals(result, 8);
        result = testTree.queryTree(1, 2, 3);
        Assertions.assertEquals(result, 5 ^ 3);
    }
}