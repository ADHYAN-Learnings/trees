package org.adhyan.hackerrank.trees;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.*;

import org.adhyan.hackerrank.trees.BalancedForestTest.Node;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'balancedForest' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY c
     *  2. 2D_INTEGER_ARRAY edges
     */

	static class Node {
		long cost;
		boolean visited=false , v2=false;
		ArrayList<Integer> adjacent = new ArrayList<Integer>();
				
		public Node(long cost){
			this.cost=cost;
		}
	}
	
   static long minimum , totalSum;
   static HashSet<Long> s = new HashSet<>();
   static HashSet<Long> q = new HashSet<>();
   
   public static long balancedForest(List<Integer> c, List<List<Integer>> edges) {
    
   List<Node> nodes = c.stream().map(data->new Node(data)).collect(Collectors.toList());
   
    edges.stream().forEach(edgeData->{
       int a = edgeData.get(0) - 1;
  	   int b = edgeData.get(1) - 1;
  	   nodes.get(a).adjacent.add(b);
  	   nodes.get(b).adjacent.add(a);
    });
    
    minimum = totalSum = dfs(0,nodes);
 //   System.out.println(minimum+" : "+totalSum);
    solve(0,nodes);
    
 /**   for(Node node : nodes) {
    	System.out.println(node.cost+" : "+node.visited+" : "+node.v2+" : "+node.adjacent);
    } **/
      return minimum==totalSum ?-1:minimum ;
    }
   
   private static void solve(int p, List<Node> nodes) {
	  Node node = nodes.get(p);
	  
	  if(node.v2) {
		  return;
	  }
	  
	  node.v2 = true;
      
//	  System.out.println((totalSum%2==0 && node.cost==(totalSum/2))+" :: cost::"+node.cost);
     if(totalSum%2==0 && node.cost==(totalSum/2)) {
    	 System.out.println("Minimum : 1 "+minimum);
    	 minimum = Math.min(minimum, totalSum/2);
     }
     
  //   System.out.println((s.contains(node.cost))+"  == "+node.cost);
     if(s.contains(node.cost)) {
    	 if((3*node.cost - totalSum) >= 0) {
    		 System.out.println("Minimum : 2 "+minimum);
    		 minimum = Math.min(minimum, 3*node.cost-totalSum);
    	 }
     }
    
//     System.out.println((s.contains(totalSum - 2*node.cost) || q.contains(totalSum - node.cost))+" ::"+node.cost); 
     if(s.contains(totalSum - 2*node.cost) || q.contains(totalSum - node.cost)) {
    	 if((3*node.cost - totalSum) >= 0) {
    		 System.out.println("Minimum : 3 "+minimum);
    		 minimum = Math.min(minimum, 3*node.cost-totalSum);
    	 }
     }
    
     if((totalSum-node.cost) %2 ==0) {
    	if(s.contains((totalSum-node.cost)/2) || q.contains((totalSum + node.cost) /2)) {
    		if(((totalSum-node.cost)/2 - node.cost) >= 0) {
    			System.out.println("Minimum : 4 "+minimum);
       		 minimum = Math.min(minimum, ((totalSum-node.cost)/2 - node.cost));
       	 }
    	}
     }
     
	  q.add(node.cost);
//	  System.out.println("Add= "+node.cost);
	  for(int i=0;i<node.adjacent.size();i++) {
		  solve(node.adjacent.get(i),nodes);
	  }
	   q.remove(node.cost);
	   s.add(node.cost);
//	   System.out.println(" Remove= "+node.cost+" , Add ="+node.cost);

	   
    }

private static long dfs(int p, List<Node> nodes) {
	Node node = nodes.get(p);
	if(node.visited) {
		return 0;
	}
	node.visited = true;
	
	for(int i=0;i<node.adjacent.size();i++) {
		node.cost += dfs(node.adjacent.get(i),nodes);
	}	
	return node.cost;
 }
}

public class BalancedForest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, n - 1).forEach(i -> {
                    try {
                        edges.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.balancedForest(c, edges);

                System.out.println("Result = "+result);
   //             bufferedWriter.write(String.valueOf(result));
    //            bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
 //       bufferedWriter.close();
    }
}

