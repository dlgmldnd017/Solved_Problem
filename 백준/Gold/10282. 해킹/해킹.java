import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, D, C;
    static List<Node>[] list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            list = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list[b].add(new Node(a, s));
            }

            solve();


        }

        System.out.println(sb);
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(C, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[C] = 0;

        boolean[] visited = new boolean[N + 1];

        int cnt = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            cnt++;

            for (Node n : list[cur.e]) {
                if (visited[n.e] || dist[n.e] < dist[cur.e] + n.t) continue;

                dist[n.e] = dist[cur.e] + n.t;
                pq.add(new Node(n.e, dist[n.e]));
            }
        }

        int sum = 0;

        for (int i : dist) {
            if (i == Integer.MAX_VALUE) continue;

            if (sum < i) sum = i;
        }

        sb.append(cnt).append(" ").append(sum).append("\n");
    }
}

class Node implements Comparable<Node> {
    int e, t;

    Node(int e, int t) {
        this.e = e;
        this.t = t;
    }

    public int compareTo(Node n) {
        return this.t - n.t;
    }
}