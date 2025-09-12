import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>();

        for (String str : operations) {
            String[] s = str.split(" ");

            switch (s[0]) {
                case "I":
                    int value = Integer.parseInt(s[1]);
                    min.add(value);
                    max.add(-value);
                    break;

                case "D":
                    int command = Integer.parseInt(s[1]);

                    if (min.isEmpty()) continue;

                    if (command == 1) {
                        int p = -max.poll();
                        min.remove(p);
                    } else {
                        int p = min.poll();
                        max.remove(-p);
                    }
            }
        }

        int[] ans = new int[2];
        
        if (min.isEmpty()) {
            ans[0] = 0;
            ans[1] = 0;
        } else {
            ans[0] = -max.peek();
            ans[1] = min.peek();
        }
        
        return ans;
    }
}