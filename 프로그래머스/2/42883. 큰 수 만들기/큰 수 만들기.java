class Solution {
    public String solution(String number, int k) {
        int len = number.length();
        char[] ch = new char[len];
        int top = -1;

        for (int i = 0; i < len; i++) {
            char c = number.charAt(i);
            
            while (k > 0 && top >= 0 && ch[top] < c) {
                top--;
                k--;
            }
            
            ch[++top] = c;
        }

        if (k > 0) top -= k;

        return new String(ch, 0, top + 1);
    }
}
