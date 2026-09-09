/*
Time Complexity - O(M*N) 
Space Complexity - O(N + N^2) {Queue, Stack Space}
*/
import java.util.*;
public class floodFillAlgo {

    static class Pair{
        int i;
        int j;
        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // no:of rows
        int m = sc.nextInt();
        // no:of cols
        int n = sc.nextInt();

        int mat[][] = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter starting row: ");
        int srow = sc.nextInt();
        System.out.print("Enter starting column: ");
        int scol = sc.nextInt();
        System.out.print("Enter new color: ");
        int newColor = sc.nextInt();
        int init = mat[srow][scol];
        if(init == newColor){
            System.out.println("/nFinal Matrix");
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(srow, scol));
        mat[srow][scol] = newColor;
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            Pair point = queue.poll();
            int x = point.i;
            int y = point.j;
            for(int i = 0; i < 4; i++){
                int ni = x + dr[i];
                int nj = y + dc[i];
                if(ni >= 0 && ni < m && nj >= 0 && nj < n && mat[ni][nj] == init){
                    mat[ni][nj] = newColor;
                    queue.offer(new Pair(ni, nj));
                }
            }
        }

        System.out.println("/nFinal Matrix");
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
