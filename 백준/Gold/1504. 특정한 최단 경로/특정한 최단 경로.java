import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, E, v1, v2, ans;
    static List<Node>[] list;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, c));
            list[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int min = Integer.MAX_VALUE;

        if (!bfs(1, v1) || !bfs(v1, v2) || !bfs(v2, N)) {
            ans = -1;
            return;
        }

        min = ans;

        ans = 0;

        if (!bfs(1, v2) || !bfs(v2, v1) || !bfs(v1, N)) {
            return;
        }

        if (min < ans) ans = min;
    }

    static boolean bfs(int start, int end) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.e] < cur.c) continue;

            if (cur.e == end) {
                ans += cur.c;
                return true;
            }

            for (Node next : list[cur.e]) {
                int cost = next.c + cur.c;

                if (dist[next.e] < cost) continue;

                dist[next.e] = cost;
                pq.add(new Node(next.e, cost));
            }
        }

        return false;
    }

    static class Node implements Comparable<Node> {
        int e, c;

        Node(int e, int c) {
            this.e = e;
            this.c = c;
        }

        public int compareTo(Node n) {
            return this.c - n.c;
        }
    }
}