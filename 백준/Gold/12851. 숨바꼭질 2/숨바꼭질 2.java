import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, cnt;

    Node(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, K, visited[], ans, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;

        if (N < K) solve();
        else {
            ans = N - K;
            cnt = 1;
        }

        System.out.println(ans + "\n" + cnt);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(N, 0));

        visited = new int[100_001];

        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.cnt > ans) continue;

            if (cur.x == K) {
                if (ans >= cur.cnt) {
                    if (ans > cur.cnt) {
                        ans = cur.cnt;
                        cnt = 0;
                    }
                    cnt++;
                }
                continue;
            }

            int nextPos[] = {cur.x - 1, cur.x + 1, cur.x * 2};

            for (int next : nextPos) {
                if (next >= 0 && next <= 100_000) {
                    if (visited[next] < cur.cnt + 1) continue;

                    visited[next] = cur.cnt + 1;
                    q.add(new Node(next, cur.cnt + 1));
                }
            }
        }
    }
}