import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans, cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(N, 0));

        int visited[] = new int[100_001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if (ans < cur.t) continue;

            if (cur.n == K) {
                if (ans >= cur.t) {
                    if (ans > cur.t) {
                        ans = cur.t;
                        cnt = 0;
                    }
                    cnt++;
                }
                continue;
            }

            int nextPos[] = {cur.n - 1, cur.n + 1, cur.n * 2};

            for (int next : nextPos) {
                if (next < 0 || next > 100_000 || visited[next] < cur.t + 1) continue;

                visited[next] = cur.t + 1;
                q.add(new Node(next, cur.t + 1));
            }
        }

        sb.append(ans + "\n");
        sb.append(cnt + "\n");
    }
}

class Node {
    int n, t;

    Node(int n, int t) {
        this.n = n;
        this.t = t;
    }
}
