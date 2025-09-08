import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] ans = new int[len];
        Stack<Integer> st = new Stack<>();
        
        for (int i = 0; i < len; i++) {
            while (!st.isEmpty() && prices[i] < prices[st.peek()]) {
                ans[st.peek()] = i - st.peek();
                st.pop();
            }
            
            st.push(i);
        }
        
        while (!st.isEmpty()) {
            ans[st.peek()] = len - st.peek() - 1;
            st.pop();
        }
        
        return ans;
    }
}