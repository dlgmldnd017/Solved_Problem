import java.util.*;

class Solution {
    static int N, ans;
    static Set<Integer> set = new HashSet<>();
    
    public int solution(int[] nums) {
        N = nums.length / 2;
        
        solve(nums);
        
        return ans;
    }
    
    static void solve(int[] nums) {
        
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        ans = set.size();
        
        if(ans>N) ans = N;
    }
}