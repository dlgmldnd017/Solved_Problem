import java.util.*;

// N = 10,000
// E = 4,950
class Solution {
    public int solution(int n, int[][] costs) {
        List<Node>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) list[i] = new ArrayList<>();
        
        for (int i = 0; i < costs.length; i++) {
            int u = costs[i][0];
            int v = costs[i][1];
            
            list[u].add(new Node(v, costs[i][2]));
            list[v].add(new Node(u, costs[i][2]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        
        boolean[] visited = new boolean[n];
        
        int answer = 0, cnt = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (visited[cur.e]) continue;
            visited[cur.e] = true;
            
            answer += cur.c;
            
            if (++cnt == n) break;
            
            for (Node next : list[cur.e]) {
                if (visited[next.e]) continue;
                
                pq.add(new Node(next.e, next.c));
            }
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int e, c;
        
        Node(int e, int c) {
            this.e = e;
            this.c = c;
        }
        
        public int compareTo(Node n) {
            return this.c - n.c;
        }
    }
}