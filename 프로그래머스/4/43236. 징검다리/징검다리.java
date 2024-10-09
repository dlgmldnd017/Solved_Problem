import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int low = 1, high = distance;
        
        while(low <= high) {
            int mid = (low + high) / 2;
            
            if(getRemoveRocks(distance, rocks, mid) <= n) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return answer;
    }
    
    static int getRemoveRocks(int distance, int rocks[], int mid) {
        int cnt = 0, start = 0;
        
        for(int i = 0; i < rocks.length; i++) {
            if(rocks[i] - start < mid) {
                cnt++;
                continue;
            }
            
            start = rocks[i];
        }
        
        if(distance - start < mid) cnt++;
        
        return cnt;
    }
}