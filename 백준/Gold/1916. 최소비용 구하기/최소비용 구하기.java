import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, A, B, ans;
    static List<Node>[] list;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[A] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(A, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.e] < cur.c) continue;

            for (Node next : list[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.c) {
                    dist[next.e] = dist[cur.e] + next.c;
                    pq.add(new Node(next.e, dist[next.e]));
                }
            }
        }

        ans = dist[B];
    }
}

class Node implements Comparable<Node> {
    int e, c;

    Node(int e, int c) {
        this.e = e;
        this.c = c;
    }

    public int compareTo(Node n) {
        return this.c - n.c;
    }
}
