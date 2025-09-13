import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i] + "");
        }
        
        Collections.sort(list, new Comparator<String>() {
           public int compare(String s1, String s2) {
               String option1 = s1 + s2;
               String option2 = s2 + s1;
               return option2.compareTo(option1);
           } 
        });
        
        StringBuilder sb = new StringBuilder();
        
        for (String str : list) {
            sb.append(str);
        }
        
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }
}