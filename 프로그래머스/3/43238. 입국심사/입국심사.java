import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long ans = -1;
        
        Arrays.sort(times);
        
        long left = 0, right = (long)n * times[times.length-1];
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            long complete = 0;
            
            for(int i = 0; i < times.length; i++) complete += mid / times[i];
            
            if(complete < n) left = mid + 1;
            else {
                right = mid - 1;
                ans = mid;
            }
        }

        return ans;
    }
}