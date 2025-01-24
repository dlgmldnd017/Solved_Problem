import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder.put(x, y);
        }

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
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 1));

        boolean visited[] = new boolean[101];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.pos]) continue;
            visited[cur.pos] = true;

            for (int k = 1; k <= 6; k++) {
                int nextPos = cur.pos + k;

                if (nextPos == 100) {
                    ans = cur.cnt;
                    return;
                }

                if (nextPos >= 101 || visited[nextPos]) continue;

                if (ladder.containsKey(nextPos)) nextPos = ladder.get(nextPos);
                else if (snake.containsKey(nextPos)) nextPos= snake.get(nextPos);

                if (visited[nextPos]) continue;

                pq.add(new Node(nextPos, cur.cnt + 1));
            }
        }
    }
}

class Node implements Comparable<Node> {
    int pos, cnt;

    Node(int pos, int cnt) {
        this.pos = pos;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        return this.cnt - n.cnt;
    }
}