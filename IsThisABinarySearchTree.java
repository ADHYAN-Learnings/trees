package org.adhyan.hackerrank.trees;

import java.util.Arrays;
import java.util.Scanner;

public class IsThisABinarySearchTree {

	public static void main(String[] args) {
		
	  Scanner scanner = new Scanner(System.in);
	 
	  scanner.nextInt();
	  scanner.nextLine();
	  
	  int[] tree_data = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
	  int length = tree_data.length;
	  
	  if(sorted(tree_data,length)!=0) {
		  System.out.println("Yes");
	  } else {
		  System.out.println("No");
	  }
			  
      scanner.close();
	}

	private static int sorted(int[] tree_data, int length) {
		
		if(length==1 || length==0) {
			return 1;
		}
		if(tree_data[length-1] <= tree_data[length-2]) {
			return 0;
		}
		return sorted(tree_data,length-1);
	}

}