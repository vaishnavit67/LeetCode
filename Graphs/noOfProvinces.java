/*
Time Complexity - O(V + E)
Space Complexity - O(V)
*/

import java.util.*;

public class noOfProvinces {

    static void dfs(int node, List<List<Integer>> list, boolean vis[]){
        for(int v : list.get(node)){
            if(!vis[v]){
                vis[v] = true;
                dfs(v, list, vis);
            }
        }
    }

    static void bfs(int start, List<List<Integer>> list, boolean vis[]){
        vis[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int n = queue.poll();
            for(int v : list.get(n)){
                if(!vis[v]){
                    vis[v] = true;
                    queue.offer(v);
                }
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

        int comp = 0;
        boolean vis[] = new boolean[n+1];
        for(int i = 1; i <= n; i++){
            if(!vis[i]){
                vis[i] = true;
                dfs(i, list, vis);
                comp++;
            }
        }
        System.out.println("No:of Provinces (DFS) = " + comp);

        comp = 0;
        Arrays.fill(vis, false);
        for(int i = 1; i <= n; i++){
            if(!vis[i]){
                bfs(i, list, vis);
                comp++;
            }
        }
        System.out.println("No:of Provinces (BFS) = " + comp);
    }
}
