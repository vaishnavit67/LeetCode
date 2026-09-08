/*

*/

import java.util.*;
public class dfs {
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
    }
}
