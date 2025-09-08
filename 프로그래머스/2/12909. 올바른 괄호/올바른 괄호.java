import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                st.push(c);
            } else {
                if (!st.isEmpty() && st.peek() == '(') st.pop();
                else return false;
            }
        }

        if (!st.isEmpty()) return false;
        
        return true;
    }
}