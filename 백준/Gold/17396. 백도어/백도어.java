import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int e;
    long t;

    Node(int e, long t) {
        this.e = e;
        this.t = t;
    }

    public int compareTo(Node n) {
        if (this.t - n.t > 0) return 1;
        return -1;
    }
}

public class Main {
    static int N, M, arr[];
    static long ans;
    static List<Node> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];

        for (int i = 0; i < N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[s].add(new Node(e, t));
            list[e].add(new Node(s, t));
        }

        arr[N - 1] = 0;

        solve();

        if (ans == Long.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void solve() {
        long dist[] = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        boolean visited[] = new boolean[N];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if (arr[next.e] == 1 || dist[next.e] <= dist[cur.e] + next.t) continue;

                dist[next.e] = dist[cur.e] + next.t;
                pq.add(new Node(next.e, dist[next.e]));
            }
        }

        ans = dist[N - 1];
    }
} 