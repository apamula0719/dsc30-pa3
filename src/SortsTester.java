import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SortsTester {
    Sorts s = new Sorts();

    ArrayList<Integer> random = new ArrayList<>();
    int[] randomArray = new int[]{5, 0, 1, 6, 3, 8, 4};
    ArrayList<Integer> sorted = new ArrayList<>();
    int[] sortedArray = new int[]{0, 1, 3, 4, 5, 6, 8};
    ArrayList<Integer> reverse = new ArrayList<>();
    int[] reverseArray = new int[]{8, 6, 5, 4, 3, 1, 0};

    //Helper to create test lists easily
    public void add_list(ArrayList<Integer> a, int[] l){
        for(int i : l)
            a.add(i);
    }
    ArrayList<Integer> true_sorted = sorted;
    @Test
    public void InsertionTest() {
        add_list(random, randomArray);
        add_list(sorted, sortedArray);
        add_list(reverse, reverseArray);

        s.InsertionSort(random, 0, random.size()-1);
        assertEquals(random, true_sorted);
        s.InsertionSort(reverse, 0, random.size()-1);
        assertEquals(reverse, true_sorted);
        s.InsertionSort(sorted, 0, random.size()-1);
        assertEquals(sorted, true_sorted);
    }
    @Test
    public void QuickTest(){
        add_list(random, randomArray);
        add_list(sorted, sortedArray);
        add_list(reverse, reverseArray);

        s.QuickSort(random, 0, random.size()-1);
        assertEquals(random, true_sorted);
        s.QuickSort(reverse, 0, random.size()-1);
        assertEquals(reverse, true_sorted);
        s.QuickSort(sorted, 0, random.size()-1);
        assertEquals(sorted, true_sorted);
    }

    @Test
    public void Modified_Quicksort_Test(){
        add_list(random, randomArray);
        add_list(sorted, sortedArray);
        add_list(reverse, reverseArray);

        s.Modified_QuickSort(random, 0, random.size()-1, 2);
        assertEquals(random, true_sorted);
        s.Modified_QuickSort(reverse, 0, random.size()-1, 3);
        assertEquals(reverse, true_sorted);
        s.Modified_QuickSort(sorted, 0, random.size()-1, 4);
        assertEquals(sorted, true_sorted);
    }
    @Test
    public void CountingTest(){
        add_list(random, randomArray);
        add_list(sorted, sortedArray);
        add_list(reverse, reverseArray);


        assertEquals(s.countSort(random), true_sorted);
        assertEquals(s.countSort(reverse), true_sorted);
        assertEquals(s.countSort(sorted), true_sorted);
    }

    @Test
    public void BucketTest(){
        add_list(random, randomArray);
        add_list(sorted, sortedArray);
        add_list(reverse, reverseArray);


        assertEquals(s.bucketSort(random), true_sorted);
        assertEquals(s.bucketSort(reverse), true_sorted);
        assertEquals(s.bucketSort(sorted), true_sorted);
    }
}