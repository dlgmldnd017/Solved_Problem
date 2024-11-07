import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int low = 0, high = people.length-1;
        
        while(low <= high) {
            int sum = people[low] + people[high];
            
            if(sum <= limit) {
                low++;
                high--;
            } else {
                high--;
            }
            
            answer++;
        }
        
        return answer;
    }
}