import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        int ans = 0;
        
        while (pq.size() >= 2 && pq.peek() < K) {
            int i = pq.poll();
            int j = pq.poll();
            
            pq.add(i + (j * 2));
            
            ans++;
        }
        
        if (pq.peek() < K) return -1;
        
        return ans;
    }
}