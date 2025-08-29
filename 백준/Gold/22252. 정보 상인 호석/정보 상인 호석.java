import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static long ans;
    static Map<String, PriorityQueue<Long>> map = new HashMap<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());

        for (int q = 1; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            PriorityQueue<Long> pq = map.computeIfAbsent(name, k -> new PriorityQueue<>());

            switch (command) {
                case 1:
                    int k = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < k; i++) {
                        pq.add(-Long.parseLong(st.nextToken()));
                    }
                    break;

                case 2:
                    if (map.containsKey(name)) {
                        int b = Integer.parseInt(st.nextToken());
                        while (!pq.isEmpty() && b-- > 0) {
                            ans += (-pq.poll());
                        }
                    }
                    break;
            }
        }

        System.out.println(ans);
    }
}