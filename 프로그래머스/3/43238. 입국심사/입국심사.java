class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long low = 1, high = (long) n * times[times.length-1];
        
        while(low <= high) {
            long mid = (low + high) / 2, cnt = 0;
            
            for(int i = 0; i < times.length; i++) cnt += mid / times[i];
            
            if(cnt < n) low = mid + 1;
            else {
                high = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}