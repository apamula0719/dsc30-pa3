/*
 * NAME: TODO
 * PID:  TODO
 */

//Provided imports, feel free to use these if needed
import java.util.Collections;
import java.util.ArrayList;

/**
 * TODO: add class header
 */
public class Sorts {

    /**
     * Performs Insertion Sort on list parameter, starting from start and ending at
     * end, inclusive both
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<Integer> list, int start, int end) {
        for(int i = start; i < end; i++){
            for(int j = i+1; j > start; j--){
              if(list.get(j-1) > list.get(j)){
                  int temp = list.get(j);
                  list.set(j, list.get(j-1));
                  list.set(j-1, temp);
              }
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<Integer> list, int start, int end) {

        if (start < end) {
            int mid = start + (end - start) / 2;
            MergeSort(list, start, mid);
            MergeSort(list, mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<Integer> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<Integer> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            } else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * Performs Quick Sort on list parameter, starting from start and ending at
     * end, inclusive both
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<Integer> list, int start, int end) {
        if(start < end){
            int pivotIndex = partition(list, start, end);
            QuickSort(list, start, pivotIndex -1);
            QuickSort(list, pivotIndex + 1, end);
        }
    }

    /**
     * Partitioning helper function for Quicksort Method
     *
     * @param arr The arraylist we want to sort
     * @param l The inital index on subsection of Arraylist we want to sort
     * @param h The final index of the subsection of Arraylist we want to sort
     */
    private int partition(ArrayList<Integer> arr, int l, int h) {
        int pivot = arr.get((h+l)/2);
        while(l < h){
            while(arr.get(l) < pivot)
                l++;
            while(arr.get(h) > pivot)
                h--;
            if(l < h){
                int temp = arr.get(l);
                arr.set(l, arr.get(h));
                arr.set(h, temp);
            }
        }
        return l;
    }

    /**
     * Performs Quick Sort on list parameter, starting from start and ending at
     * end, inclusive both, switches to Insertion Sort when a sub-list is smaller
     * than the cutoff size.
     *
     * @param list The arraylist we want to sort
     */
    public void Modified_QuickSort(ArrayList<Integer> list, int start, int end, int cutoff) {
        if(end - start > cutoff){
            int pivotIndex = partition(list, start, end);
            Modified_QuickSort(list, start, pivotIndex -1, cutoff);
            Modified_QuickSort(list, pivotIndex + 1, end, cutoff);
        }
        else if(start < end){
            InsertionSort(list, start, end);
        }
    }

    /**
     * this helper finds the appropriate number of buckets you should allocate
     * based on the range of the values in the input list
     * @param list the input list to bucket sort
     * @return number of buckets
     */
    private int assignNumBuckets(ArrayList<Integer> list) {
        Integer max = Collections.max(list);
        Integer min = Collections.min(list);
        return (int) Math.sqrt(max - min) + 1;
    }

    /**
     * this helper finds the appropriate bucket index that a data should be
     * placed in
     * @param data a particular data from the input list if you are using
     *             loop iteration
     * @param numBuckets number of buckets
     * @param listMin the smallest element of the input list
     * @return the index of the bucket for which the particular data should
     * be placed in
     */
    private int assignBucketIndex(Integer data, int numBuckets, Integer listMin) {
        return (data - listMin) / numBuckets;
    }

    /**
     * Performs Bucket Sort on list parameter, starting from start and ending at
     * end, inclusive both
     *
     * @param list The arraylist we want to sort
     */
    public ArrayList<Integer> bucketSort(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(assignNumBuckets(list));
        for(int i = 0; i < assignNumBuckets(list); i++)//Fill the arraylist with arraylists
            buckets.add(new ArrayList<Integer>());
        for(int j : list)//Sort the values into buckets
            buckets.get(assignBucketIndex(j, assignNumBuckets(list), Collections.min(list))).add(j);
        for(ArrayList<Integer> arr : buckets)//Sort each bucket
            InsertionSort(arr, 0, arr.size()-1);
        ArrayList<Integer> finalArray = new ArrayList<Integer>(buckets.size());
        for(ArrayList<Integer> sub_arr : buckets)//Merge the list
            finalArray.addAll(sub_arr);
        return finalArray;
    }

    /**
     * Performs Count Sort on list parameter, starting from start and ending at
     * end, inclusive both
     *
     * @param list The arraylist we want to sort
     */
    public ArrayList<Integer> countSort(ArrayList<Integer> list) {
        int[] counting = new int[Collections.max(list)+1];
        for(int i : list)
            counting[i]++;
        int running_total = 0;
        for(int i=0; i < counting.length; i++){
            running_total+=counting[i];
            counting[i] = running_total;
        }
        int[] finalArray = new int[list.size()];
        for (Integer j : list) {
            counting[j]--;
            finalArray[counting[j]] = j;
        }
        ArrayList<Integer> finalArrayList = new ArrayList<Integer>(list.size());
        for (int i : finalArray){
            finalArrayList.add(i);
        }
        return finalArrayList;

    }
}