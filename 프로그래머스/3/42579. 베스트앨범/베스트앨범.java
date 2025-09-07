import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, PriorityQueue<Node>> m2 = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            m1.put(genres[i], m1.getOrDefault(genres[i], 0) + plays[i]);
            m2.computeIfAbsent(genres[i], k -> new PriorityQueue<>()).add(new Node(i, plays[i]));
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(m1.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : list) {
            String gen = e.getKey();
            PriorityQueue<Node> pq = m2.get(gen);

            int size = 0;
            while (!pq.isEmpty() && size < 2) {
                ans.add(pq.poll().idx);
                size++;
            }
        }

        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    static class Node implements Comparable<Node> {
        int idx, cnt;

        Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        public int compareTo(Node n) {
            if (n.cnt == this.cnt) return this.idx - n.idx;
            return n.cnt - this.cnt;
        }
    }
}