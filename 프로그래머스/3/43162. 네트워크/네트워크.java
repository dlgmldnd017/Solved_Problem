import java.util.*;

class Solution {
    static int N, ans;
    static List<Integer> list[];
    static boolean visited[];
    
    public int solution(int n, int[][] computers) {
        N = n;
        
        list = new ArrayList[N];
        
        for (int i = 0; i < N; i++) list[i] = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                
                if (computers[i][j] == 1) list[i].add(j);
            }
        }
        
        visited = new boolean[N];
        
        ans = 1;
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            
            bfs(i);
            
            for (boolean b : visited) {
                if (b) continue;
                
                ans++;
                break;
            }
        }
        
        return ans;
    }
    
    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (visited[cur]) continue;
            visited[cur] = true;
            
            for (int next : list[cur]) {
                if (visited[next]) continue;
                
                q.add(next);
            }
        }
    }
}