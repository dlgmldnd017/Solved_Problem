import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 10_000_000;

    static int N, E, v1, v2, ans;
    static List<Node> list[];

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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[] dist1 = Dijkstra(1);
        int[] dist2 = Dijkstra(v1);
        int[] dist3 = Dijkstra(N);

        int noc1 = dist1[v1] + dist2[v2] + dist3[v2];
        int noc2 = dist1[v2] + dist2[v2] + dist3[v1];

        if (noc1 >= INF && noc2 >= INF) ans = -1;
        else ans = Math.min(noc1, noc2);
    }

    static int[] Dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.e] < cur.w) continue;

            for (Node next : list[cur.e]) {
                int cost = dist[cur.e] + next.w;
                if (dist[next.e] <= cost) continue;

                dist[next.e] = cost;
                pq.add(new Node(next.e, cost));
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}