import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int idx;
    static long ans;
    static Map<String, Integer> map = new HashMap<>();
    static List<PriorityQueue<Integer>> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());

        for (int q = 1; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (!map.containsKey(name)) {
                map.put(name, idx++);
                list.add(new PriorityQueue<>());
            }

            int pos = -1;
            switch (command) {
                case 1:
                    int k = Integer.parseInt(st.nextToken());

                    pos = map.get(name);
                    for (int i = 0; i < k; i++) {
                        list.get(pos).offer(-Integer.parseInt(st.nextToken()));
                    }

                    break;

                case 2:
                    int b = Integer.parseInt(st.nextToken());
                    pos = map.get(name);

                    PriorityQueue<Integer> pq = list.get(pos);
                    while (!pq.isEmpty() && b-- > 0) {
                        ans += (-pq.poll());
                    }

                    break;
            }
        }

        System.out.println(ans);
    }
}