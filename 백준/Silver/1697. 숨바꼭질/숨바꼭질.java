import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x, cnt;

    Node(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, K, ans;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        if (N < K) solve();
        else ans = N - K;

        System.out.println(ans);
    }

    static void solve() {
        Queue<Node> pq = new ArrayDeque<>();
        pq.add(new Node(N, 0));

        visited = new boolean[100_001];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == K) {
                ans = cur.cnt;
                return;
            }

            if (visited[cur.x]) continue;
            visited[cur.x] = true;

            if (cur.x - 1 >= 0) pq.add(new Node(cur.x - 1, cur.cnt + 1));
            if (cur.x + 1 <= 100_000) pq.add(new Node(cur.x + 1, cur.cnt + 1));
            if (cur.x * 2 <= 100_000) pq.add(new Node(cur.x * 2, cur.cnt + 1));
        }
    }
}