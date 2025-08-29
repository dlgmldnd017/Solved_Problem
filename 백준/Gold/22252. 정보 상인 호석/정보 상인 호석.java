import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int idx;
    static long ans;
    static Map<String, Integer> map = new HashMap<>();
    static List<PriorityQueue<Long>> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());

        for (int q = 1; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            int pos = -1;
            switch (command) {
                case 1:
                    if (!map.containsKey(name)) {
                        pos = idx++;
                        map.put(name, pos);
                        list.add(new PriorityQueue<>());
                    } else {
                        pos = map.get(name);
                    }

                    int k = Integer.parseInt(st.nextToken());

                    for (int i = 0; i < k; i++) {
                        list.get(pos).add(-Long.parseLong(st.nextToken()));
                    }

                    break;

                case 2:
                    if (map.containsKey(name)) {
                        int b = Integer.parseInt(st.nextToken());
                        pos = map.get(name);

                        PriorityQueue<Long> pq = list.get(pos);
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