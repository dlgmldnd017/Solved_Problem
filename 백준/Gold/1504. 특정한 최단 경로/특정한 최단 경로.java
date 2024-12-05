import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, E, v1, v2, ans;
    static List<Node> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

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

        int dist1 = solve(1, v1);
        dist1 += solve(v1, v2);
        dist1 += solve(v2, N);

        int dist2 = solve(1, v2);
        dist2 += solve(v2, v1);
        dist2 += solve(v1, N);

        if (dist1 >= 100_000_000 && dist2 >= 100_000_000) ans = -1;
        else ans = Math.min(dist1, dist2);

        System.out.println(ans);
    }

    static int solve(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int dist[] = new int[N + 1];
        Arrays.fill(dist, 100_000_000);
        dist[start] = 0;

        visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            ans += cur.d;

            for (Node next : list[cur.e]) {
                if (visited[next.e] | (dist[next.e] <= dist[cur.e] + next.d)) continue;

                dist[next.e] = dist[cur.e] + next.d;
                pq.add(new Node(next.e, dist[next.e]));
            }
        }

        return dist[end];
    }
}

class Node implements Comparable<Node> {
    int e, d;

    Node(int e, int d) {
        this.e = e;
        this.d = d;
    }

    public int compareTo(Node n) {
        return this.d - n.d;
    }
}