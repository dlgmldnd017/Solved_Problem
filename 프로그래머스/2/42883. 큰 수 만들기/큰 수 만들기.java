class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        int start = 0, max;
        for (int i = 0; i < number.length() - k; i++) {
            max = 0;
            
            for (int j = start; j <= i + k; j++) {
                int target = number.charAt(j) - '0';
                
                if(max < target) {
                    max = target;
                    start = j + 1;
                }
            }
            
            sb.append(max+"");
        }
        
        return sb.toString();
    }
}