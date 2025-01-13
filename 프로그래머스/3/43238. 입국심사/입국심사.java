class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 0, right = (long) n * times[times.length - 1];
        
        while (left <= right) {
            long mid = (left + right) / 2, cnt = 0;
            
            for (int i = 0; i < times.length; i++) cnt += mid / times[i];
            
            if (cnt >= n) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}