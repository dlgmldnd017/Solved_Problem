import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int answer = 0, len = citations.length;
        
        for (int i = 0; i < citations.length; i++) {
            int cnt = Math.min(citations[i], len - i);
            if (answer < cnt) answer = cnt;
        }
            
        return answer;
    }
}

// 0 1 5 5 6