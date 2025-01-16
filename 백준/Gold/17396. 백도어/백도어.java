import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static long ans;
    static boolean view[];
    static List<Node> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        view = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) view[i] = true;
        }

        list = new ArrayList[N];

        for (int i = 0; i < N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, t));
            list[b].add(new Node(a, t));
        }

        solve();

        System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }

    static void solve() {
        long dist[] = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.t > dist[cur.e]) continue;

            for (Node next : list[cur.e]) {
                if (!view[next.e] && dist[next.e] > dist[cur.e] + next.t) {
                    dist[next.e] = dist[cur.e] + next.t;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }

        ans = dist[N - 1];
    }
}

class Node implements Comparable<Node> {
    int e;
    long t;

    Node(int e, long t) {
        this.e = e;
        this.t = t;
    }

    public int compareTo(Node n) {
        return Long.compare(this.t, n.t);
    }
}