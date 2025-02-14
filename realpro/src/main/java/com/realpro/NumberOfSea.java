package com.realpro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberOfSea {
    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1, 1},
                {0, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 0, 0}
        };
        System.out.println("Test case 1: " + pondSizes(grid1));
    }
    public static List<Integer> pondSizes(int[][] land) {
        List<Integer> pondSizes = new ArrayList<>();
        //your code here
        int countDataGrest1 = 0;
        for(int[] i : land) {
        	List<Integer> list = Arrays.stream(i)
            .boxed()
            .collect(Collectors.toList());
        	
        	for(int j: list) {
            	if(j > 1) {
            		countDataGrest1++;
            	}  	
        	}
        }
        pondSizes.add(countDataGrest1);
        pondSizes.add(land.length);
        
        return pondSizes;
    }
}