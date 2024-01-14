import java.util.ArrayList;

public class Questions {
    // leet 200
    public void dfs(int i, int j, char[][] grid, boolean[][] vis){
        if(grid[i][j]=='0' || vis[i][j]){
            return;
        }

        // grid[i][j] = '0';
        vis[i][j] = true;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int[] dir:dirs){
            int nRow = i + dir[0];
            int nCol = j + dir[1];

            if(nRow>=0 && nRow<vis.length && nCol>=0 && nCol<grid[0].length){
                dfs(nRow,nCol,grid,vis);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]=='1' && !vis[i][j]){
                    dfs(i,j,grid,vis);
                    count++;
                }
            }
        }

        return count;
    }

    // leetcode 695
    public int dfs2(int i, int j, int[][] grid){
        if(grid[i][j]==0){
            return 0;
        }

        grid[i][j] = 0;
        
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int myArea = 0;

        for(int[] dir:dirs){
            int nRow = i + dir[0];
            int nCol = j + dir[1];

            if(nRow>=0 && nRow<grid.length && nCol>=0 && nCol<grid[0].length){
                int nbrArea = dfs2(nRow,nCol,grid);
                myArea += nbrArea;
            }
        }

        return myArea + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int maxArea = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]==1){
                    int area = dfs2(i,j,grid);
                    maxArea = Math.max(area,maxArea);
                }
            }
        }

        return maxArea;
    }

    // leetcode 130 
    public void solve(char[][] board) {
        
    }

    // leetcode 207
    public boolean checkCycle(int src, int[] vis, ArrayList<Integer>[] graph){
        if(vis[src] == 1){
            return true;
        } else if(vis[src]==2){
            return false;
        }

        vis[src] = 1;

        for(int nbr: graph[src]){
            if(checkCycle(nbr, vis, graph)){
                return true;
            }
        }

        vis[src] = 2;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();    
        }

        for(int[] pre:prerequisites){
            graph[pre[1]].add(pre[0]);
        }

        int[] vis = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            if(vis[i]==0){
                if(checkCycle(i,vis,graph)){
                    return false;
                }
            }
        }

        return true;
    }

    // leetcode 210
    boolean isCycle = false;
    public void topoDFS(int src, int[] vis, ArrayList<Integer>[] graph,ArrayList<Integer> ans){
        if(vis[src] == 1){
            isCycle = true;
            return;
        } else if(vis[src]==2){
            return;
        }

        vis[src] = 1;

        for(int nbr: graph[src]){
            topoDFS(nbr, vis, graph, ans);
        }

        vis[src] = 2;
        ans.add(src);
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();    
        }

        for(int[] pre:prerequisites){
            graph[pre[1]].add(pre[0]);
        }

        int[] vis = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            if(vis[i]==0){
                topoDFS(i,vis,graph,ans);
            }
        }

        int[] ansArray = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            ansArray[i] = ans.get(numCourses-i-1); // reversing ans arraylist
        }

        if(isCycle){
            return new int[]{};
        } else {
            return ansArray;
        }
    }

    // https://leetcode.com/problems/find-eventual-safe-states/description/
    public static void main(String[] args) {
        
    }
}
