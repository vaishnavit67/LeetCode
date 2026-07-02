// The Question
/*
3286. Find a Safe Walk Through a Grid
You are given an m x n binary matrix grid and an integer health.
You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.
Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.

Example 1:
Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
Output: true
Explanation:
The final cell can be reached safely by walking along the gray cells below.

Example 2:
Input: grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3
Output: false
Explanation:
A minimum of 4 health points is needed to reach the final cell safely.

Example 3:
Input: grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
Output: true
Explanation:
The final cell can be reached safely by walking along the gray cells below.


Any path that does not go through the cell (1, 1) is unsafe since your health will drop to 0 when reaching the final cell.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 50
2 <= m * n
1 <= health <= m + n
grid[i][j] is either 0 or 1.
*/

// The Approach
/*
Check if the health is less than or equal to the first cell's, if yes we could never reach the end of the grid, so return false
Use a Deque here, so that you could add elements both at the end and at the front
The goal is to find if we could reach the end with minimum cost, and the cost should be less than health so that a walk can be made
Initialize 2D matrix to save the costs and fill it with the MAX integer value, add the first element's (0,0) value to the cost matrix and push it into the deque
Loop the deque until it's empty 
    - check if the destination (m-1, n-1) is reached, if yes verify if the cost is less than the health
    - for every index (i, j) traverse all directions {(i+1, j), (i-1, j), (i, j+1), (i, j-1)}
        - if the direction is possible, check if adding cost to the current index doesn't exceed the direction's cost (this happens only when the direction has already been computed and has a min value, overwriting it with a higher cost would be meaningless)
        - check if the cost of the direction is 0
            - if 0, add it to the front of the deque, (so that we reach the destination with minimum cost, hoping it would be less than the health) 
            - if not, add it to the back of the deque

return true if health is less than the min cost of the last index (m-1, n-1)
*/

// The Solution
class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        Deque<int[]> deque = new LinkedList<>();
        if(health - grid.get(0).get(0) <= 0) return false;
        int minCost[][] = new int[m][n];
        for(int cost[] : minCost){
            Arrays.fill(cost, Integer.MAX_VALUE);
        }
        minCost[0][0] = grid.get(0).get(0);
        deque.offerFirst(new int[]{0, 0});

        int x[] = {-1,1,0,0};
        int y[] = {0,0,-1,1};

        while(!deque.isEmpty()){
            int curr[] = deque.pollFirst();
            int r = curr[0], c = curr[1];

            if(r == m-1 && c == n-1){
                return (minCost[r][c] < health);
            }

            for(int i = 0; i < 4; i++){
                int nr = r + x[i], nc = c + y[i];
                if(nr >= 0 && nr < m && nc >= 0 && nc < n){
                    int cost = grid.get(nr).get(nc);
                    if(minCost[r][c] + cost < minCost[nr][nc]){
                        minCost[nr][nc] = minCost[r][c] + cost;
                        if(cost == 0){
                            deque.offerFirst(new int[]{nr, nc});
                        }
                        else{
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return minCost[m-1][n-1] < health;
    }
}