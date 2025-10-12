import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static Map<Integer, Integer> leadder, snake;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        leadder = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            leadder.put(x, y);
        }

        snake = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snake.put(u, v);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0));

        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.pos == 100) {
                ans = cur.cnt;
                return;
            }

            for (int k = 1; k <= 6; k++) {
                int nextPos = cur.pos + k;

                if (nextPos > 100 || visited[nextPos]) continue;

                if (leadder.containsKey(nextPos)) {
                    int jumpPos = leadder.get(nextPos);
                    visited[jumpPos] = true;
                    q.add(new Node(jumpPos, cur.cnt + 1));
                } else if (snake.containsKey(nextPos)) {
                    int jumpPos = snake.get(nextPos);
                    visited[jumpPos] = true;
                    q.add(new Node(jumpPos, cur.cnt + 1));
                } else {
                    visited[nextPos] = true;
                    q.add(new Node(nextPos, cur.cnt + 1));
                }
            }
        }
    }

    static class Node {
        int pos, cnt;

        Node(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}