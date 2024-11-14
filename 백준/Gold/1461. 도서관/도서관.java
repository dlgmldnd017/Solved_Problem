import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;

    static PriorityQueue<Integer> pq = new PriorityQueue<>((p1, p2) -> p2 - p1);
    static PriorityQueue<Integer> nq = new PriorityQueue<>((p1, p2) -> p2 - p1);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num > 0) pq.add(num);
            else nq.add(Math.abs(num));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int max = Integer.MIN_VALUE;

        if (pq.isEmpty()) max = nq.peek();
        else if (nq.isEmpty()) max = pq.peek();
        else max = Math.max(pq.peek(), nq.peek());

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            for (int i = 0; i < M - 1 && !pq.isEmpty(); i++) pq.poll();

            ans += cur * 2;
        }

        while (!nq.isEmpty()) {
            int cur = nq.poll();

            for (int i = 0; i < M - 1 && !nq.isEmpty(); i++) nq.poll();

            ans += cur * 2;
        }

        ans -= max;
    }
}
