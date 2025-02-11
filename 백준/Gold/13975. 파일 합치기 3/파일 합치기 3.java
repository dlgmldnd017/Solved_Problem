import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static long ans;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) pq.add(Long.parseLong(st.nextToken()));

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        while (pq.size() != 1) {
            long x1 = pq.poll();
            long x2 = pq.poll();

            pq.add(x1 + x2);

            ans += x1 + x2;
        }

        pq.poll();

        sb.append(ans + "\n");

        ans = 0;
    }
}