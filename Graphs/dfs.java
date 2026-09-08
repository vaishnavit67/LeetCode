/*
Time Complexity - O(V + 2*E)
Space Complexity - O(2*E + V + N) {Adjacency list, visited array, Recursion}
*/

import java.util.*;
public class dfs {

    static void dfsTraversal(int node, List<List<Integer>> list, boolean vis[]){
        System.out.print(node + " ");
        for(int v : list.get(node)){
            if(!vis[v]){
                vis[v] = true;
                dfsTraversal(v, list, vis);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        List<List<Integer>> list = new ArrayList<>();
        // no:of nodes
        int n = sc.nextInt();
        // no:of edges
        int m = sc.nextInt();

        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            list.get(u).add(v);
            list.get(v).add(u);
        }
        System.out.println();

        // Graph Output
        for(int i = 1; i <= n; i++){
            List<Integer> sub = list.get(i);
            System.out.print(i + ": ");
            for(int j : sub){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();

        // DFS Traversal - check method dfstraversal() method
        boolean vis[] = new boolean[n+1];
        System.out.print("Enter the starting node (1 - n): ");
        int start = sc.nextInt();
        vis[start] = true;
        dfsTraversal(start, list, vis);
    }
}
