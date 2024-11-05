import java.util.*;

class Node implements Comparable<Node> {
    int end, cost;
    
    Node(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }
    
    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<Node> list[] = new ArrayList[n];
        
        for(int i = 0; i < n; i++) list[i] = new ArrayList<>();
        
        for(int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];
            
            list[start].add(new Node(end, cost));   
            list[end].add(new Node(start, cost));   
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        
        boolean visited[] = new boolean[n];
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(visited[cur.end]) continue;
            visited[cur.end] = true;
            
            answer += cur.cost;
            
            for(Node next : list[cur.end]) {
                if(visited[next.end]) continue;
                
                pq.add(new Node(next.end, next.cost));
            }
        }
        
        return answer;
    }
}