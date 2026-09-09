import java.util.*;
public class noOfIslands {
    static void traverse(int i, int j, boolean vis[][], int mat[][]){
        /*
        (-1,-1)  (-1,0)  (-1,+1)
        ( 0,-1)  (i,j)  ( 0,+1)
        (+1,-1)  (+1,0)  (+1,+1)
        */
        int dr[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int dc[] = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int k = 0; k < 8; k++){
            int ni = i + dr[k];
            int nj = j + dc[k];

            if(ni >= 0 && nj >= 0 && ni < vis.length && nj < vis[0].length && mat[ni][nj] == 1 && !vis[ni][nj]) {
                vis[ni][nj] = true;
                traverse(ni, nj, vis, mat);
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int mat[][] = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = sc.nextInt();
            }
        }

        boolean vis[][] = new boolean[m][n];

        int island = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1 && !vis[i][j]){
                    vis[i][j] = true;
                    traverse(i, j, vis, mat);
                    island++;
                }
            }
        }
        System.out.println("No:of islands = " + island);
    }
}
