import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int idx = 0, len = jobs.length, t = 0, ans = 0;
        
        while (idx < len || !pq.isEmpty()) {
            while (idx < len && jobs[idx][0] <= t) {
                pq.add(new Node(jobs[idx][1], jobs[idx][0], idx++));
            }
            
            if (!pq.isEmpty()) {
                Node cur = pq.poll();
                
                t += cur.tt;
                
                ans += t - cur.at;
            } else {
                t = jobs[idx][0];
            }
        }
        
        return ans / len;
    }
    
    static class Node implements Comparable<Node> {
        int tt, at, num;
        
        Node(int tt, int at, int num) {
            this.tt = tt;
            this.at = at;
            this.num = num;
        }
        
        public int compareTo(Node n) {
            if(this.tt == n.tt) {
                return this.at - n.at;
            }
            return this.tt - n.tt;
        }
    }
}