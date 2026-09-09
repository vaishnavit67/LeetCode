import java.util.*;
public class rottenOranges {

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
    
        int grid[][] = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        // 0 - empty cell, 1 - fresh, 2 - rotten
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};

        Queue<Pair> queue = new LinkedList<>();
        int time = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2) queue.offer(new Pair(i, j)); 
            }
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0; k < size; k++){
                Pair point = queue.poll();
                int p1 = point.i;
                int p2 = point.j;

                for(int i = 0; i < 4; i++){
                    int ni = p1 + dr[i];
                    int nj = p2 + dc[i];
                    if(ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 1){
                        grid[ni][nj] = 2;
                        queue.offer(new Pair(ni, nj));
                    }
                }
            }
            time++;
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1) {
                    System.out.println("Cannot be done");
                    return;
                }
            }
        }
        if(time == 0) System.out.println("Time: " + 0);
        else System.out.println("Time: " + (time-1));
    }
}