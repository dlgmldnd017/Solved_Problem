import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, total;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) break;

            total = 0;

            pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                total += z;

                pq.add(new Node(x, y, z));
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        parent = new int[M + 1];

        for (int i = 1; i <= M; i++) parent[i] = i;

        int sum = 0, cnt = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (union(cur.y, cur.x)) {
                sum += cur.z;

                if (cnt == M - 1) break;
            }
        }

        sb.append(total - sum).append("\n");
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x < y) parent[y] = x;
        else parent[x] = y;

        return true;
    }
}

class Node implements Comparable<Node> {
    int x, y, z;

    Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compareTo(Node n) {
        return this.z - n.z;
    }
}