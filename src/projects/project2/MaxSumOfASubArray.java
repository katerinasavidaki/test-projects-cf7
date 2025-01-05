package projects.project2;

import java.util.Arrays;

/**
 * Finds the contiguous sub-array with the largest sum
 * from a given int[] array, using the Kadane's algorithm.
 */
public class MaxSumOfASubArray {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] maxSumSubArray = findMaxSumSubArray(arr);
        System.out.println("The sub-array with the maximum sum is: ");
        printArray(maxSumSubArray);
        System.out.println("\nThe maximum sum of the sub-array is: " + findMaxSum(maxSumSubArray));

    }

    /**
     * we use localSum to calculate the sum of every element in the array
     * we use globalSum for the maxSum
     * endIndex is used to keep track the end of the sub-array
     * startIndex is used to keep track the 1st value of the sub-array
     * We need a temporary variable so that we
     * @param array of integers
     * @return the int[] maxSumSubArray
     */
    public static int[] findMaxSumSubArray (int[] array) {
        int localSum = array[0];
        int globalSum = array[0];
        int endIndex = 0;
        int startIndex = 0;
        int temp = 0;

        for (int i = 1; i < array.length; i++) {
            localSum = Math.max(array[i], array[i] + localSum);     // we use Math.max to find the maxSum between the
                                                                    //  current value of the array and the previous sum

            if (localSum == array[i]) {      //if the localSum at the i index is equal to the array[i] (value)
                temp = i;                   //then we set temp = i so that we keep track of the start index of sub-array
            }
            if (localSum > globalSum) {       //if the localSum at the i index is greater than the globalSum
                globalSum = localSum;         // we update the globalSum and the startIndex of the finalSub-array
                startIndex = temp;            //as well as the end index of the sub-array at the i index.
                endIndex = i;
            }
        }
        return Arrays.copyOfRange(array, startIndex, endIndex + 1);
        // return the final array with the end index included (endIndex + 1)
    }

    public static int findMaxSum(int[] array) {
        int sum = array[0];
        int maxSum = array[0];

        for (int i = 1; i < array.length; i++) {
            if (sum < 0) {
                sum = array[i];
            } else {
                sum = sum + array[i];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void printArray(int[] array) {
        for (int el : array) {
            System.out.print(el + " ");
        }
    }
}
