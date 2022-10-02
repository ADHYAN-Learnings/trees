package org.adhyan.hackerrank.trees;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IsThisABinarySearchTree {

	public static void main(String[] args) {
		
	  Scanner scanner = new Scanner(System.in);
	 
	  scanner.nextInt();
	  scanner.nextLine();
	  
	  int[] tree_data = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
	  int length = tree_data.length;
	  
	  boolean isSorted = length==0?true:IntStream.range(0, length-1).noneMatch(i->tree_data[i]>=tree_data[i+1]);
	  System.out.println(isSorted?"Yes":"No");
			  
      scanner.close();
	}
}
