import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, cnt;
    String str;

    Node(int x, int cnt, String str) {
        this.x = x;
        this.cnt = cnt;
        this.str = str;
    }
}

public class Main {
    static int N, K;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N < K) solve();
        else {
            StringBuilder sb = new StringBuilder();

            for (int i = N; i >= K; i--) sb.append(i + " ");

            System.out.println((N-K) + "\n" + sb);
        }
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(N, 0, N + ""));

        visited = new boolean[100_001];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == K) {
                System.out.println(cur.cnt + "\n" + cur.str);
                return;
            }

            if (visited[cur.x]) continue;
            visited[cur.x] = true;

            int nextPos[] = {cur.x - 1, cur.x + 1, cur.x * 2};

            for (int next : nextPos) {
                if (next < 0 || next > 100_000) continue;

                q.add(new Node(next, cur.cnt + 1, cur.str + " " + next));
            }
        }
    }
}