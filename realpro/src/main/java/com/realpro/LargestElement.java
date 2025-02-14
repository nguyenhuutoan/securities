package com.realpro;

public class LargestElement {

    // Function to find the largest element
    public static int largest(int[] arr, int i) {
      
        // Last index returns the element
        if (i == arr.length - 1) {
            return arr[i];
        }

        // Find the maximum from the rest of the array
        int recMax = largest(arr, i + 1);

        // Compare with i-th element and return
        return Math.max(recMax, arr[i]);
    }

    // Driver Code
    public static void main(String[] args) {
        int[] arr = {10, 324, 45,  9808, 90};
        System.out.println("Largest in given array is " + largest(arr, 0));
    }
}