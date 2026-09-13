/*
NOTE: Works only for a connected graph

Time Complexity - O(N + V + 2E)
Space Complexity - O()
*/

import java.util.*;
public class cycleDetectionBFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no:of vertices: ");
        int ver = sc.nextInt();
        System.out.print("Enter no:of edges: ");
        int edges = sc.nextInt();


        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= ver; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < edges; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            list.get(u).add(v);
            list.get(v).add(u);
        }
        System.out.println();

        // Graph Output
        for(int i = 1; i < ver; i++){
            List<Integer> sub = list.get(i);
            System.out.print(i + ": ");
            for(int x : sub) System.out.print(x + " ");
            System.out.println();
        }
        System.out.println();

        boolean[] vis = new boolean[ver+1];
        int parent[] = new int[ver+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        vis[1] = true;
        parent[1] = -1;
        while(!queue.isEmpty()){
            int x = queue.poll();
            for(int n : list.get(x)){
                if(parent[x] == n) continue;
                else{
                    if(vis[n]) {
                        System.out.println("Cycle Detected");
                        return;
                    }
                    else{
                        queue.offer(n);
                        vis[n] = true;
                        parent[n] = x;
                    }
                }
            }
        }
        System.out.println("No cycle detected");
    }
}