/*
Space Complexity - O(n^2)
*/

import java.util.*;
public class adjmat{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        // no:of nodes
        int n = sc.nextInt();
        // no:of edges
        int m = sc.nextInt();

        int adj[][] = new int[n+1][n+1];
        
        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();

            adj[u][v] = 1;
            adj[v][u] = 1;
        }
        System.out.println();

        // Graph Output
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }
}