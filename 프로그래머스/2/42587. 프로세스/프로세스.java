import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Node> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Node(i, priorities[i]));
            pq.add(-priorities[i]);
        }
        
        int i = -pq.poll();
        int j = 1;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (cur.cnt != i) {
                q.add(cur);
            } else {
                if (cur.idx == location) return j;
                
                j++;
                i = -pq.poll();
            }
        }
        
       return j;
    }
    
    static class Node implements Comparable<Node> {
        int idx, cnt;
        
        Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
        
        public int compareTo(Node n) {
            return n.cnt - this.cnt;
        }
    }
}