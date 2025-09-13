import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        List<Integer> list = new ArrayList<>();
        
        for (int a = 0; a < commands.length; a++) {
            int i = commands[a][0] - 1;
            int j = commands[a][1] - 1;
            int k = commands[a][2] - 1;
            
            while (i <= j) {
                list.add(array[i++]);
            }
            
            Collections.sort(list);
            
            answer[a] = list.get(k);
            
            list.clear();
        }
        
        return answer;
    }
}