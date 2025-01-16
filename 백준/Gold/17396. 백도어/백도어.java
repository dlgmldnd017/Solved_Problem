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

        System.out.println(ans == 0 ? -1 : ans);
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        boolean visited[] = new boolean[N];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.e == N - 1) {
                ans = cur.t;
                return;
            }

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            for (Node next : list[cur.e]) {
                if (visited[next.e] || view[next.e]) continue;

                pq.add(new Node(next.e, next.t + cur.t));
            }
        }
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