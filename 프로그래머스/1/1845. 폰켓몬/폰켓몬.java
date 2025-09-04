import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        int len = nums.length;
        
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < len; i++) {
            set.add(nums[i]);
        }
        
        len /= 2;
        
        if (set.size() < len) {
            answer = set.size();
        } else {
            answer = len;
        }
        
        return answer;
    }
}