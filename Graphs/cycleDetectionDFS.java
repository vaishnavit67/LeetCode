import java.util.*;
public class cycleDetectionDFS {

    static boolean detectCycle(int i, List<List<Integer>> graph, boolean vis[], int par[]){
        for(int x : graph.get(i)){
            if(!vis[x]){
                vis[x] = true;
                par[x] = i;
                if(detectCycle(x, graph, vis, par)) return true;
            }
            else{
                if(par[i] != x) return true;
            }
        }
        return false;
    }
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

        boolean vis[] = new boolean[ver+1];
        int parent[] = new int[ver+1];
        for(int i = 1; i <= ver; i++){
            if(!vis[i]){
                if(detectCycle(i, list, vis, parent)){
                    System.out.println("Cycle Detected");
                    return;
                }
            } 
        }
        System.out.println("No cycle detected");
    }
}
