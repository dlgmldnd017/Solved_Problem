import java.util.*;

class Node {
    int y, x, cnt;
    
    Node (int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}

class Solution {
    static int N, M;
    
    static int dy[] = {0, 0, -1, 1};
    static int dx[] = {-1, 1, 0, 0};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1));
    
        boolean visited[][] = new boolean[N][M];
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + cur.y;
                int nx = dx[k] + cur.x;
                
                if (ny == N-1 && nx == M-1) return cur.cnt + 1;
                
                if (!inRange(ny,nx) || visited[ny][nx] || maps[ny][nx] == 0) continue;
                
                q.add(new Node(ny, nx, cur.cnt + 1));
                visited[ny][nx] = true;
            }
        }
        return -1;
    }
    
    static boolean inRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}