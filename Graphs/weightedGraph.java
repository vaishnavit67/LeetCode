import java.util.*;

class GraphNode{
    int v;
    int w;
    GraphNode(int v, int w){
        this.v = v;
        this.w = w;
    }
}

public class weightedGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // no:of vertices
        int n = sc.nextInt();
        // no:of edges
        int m = sc.nextInt();

        int[][] mat = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            mat[u][v] = w;
            mat[v][u] = w;
        }
        System.out.println();

        System.out.println("Adjacency Matrix:");
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        List<List<GraphNode>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            list.get(u).add(new GraphNode(v, w));
            list.get(v).add(new GraphNode(u, w));
        }
        System.out.println();

        System.out.println("Adjacency List:");
        for(int i = 1; i <= n; i++){
            System.out.print(i + ": ");
            List<GraphNode> sub = list.get(i);
            for(GraphNode node : sub){
                System.out.print("(" + node.v + ", " + node.w + ") ");
            }
            System.out.println();
        }
    }
}
