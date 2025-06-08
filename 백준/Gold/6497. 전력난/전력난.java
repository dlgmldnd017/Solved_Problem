import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N, ans;
    static int[] parents;
    static PriorityQueue<Node> pq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) {
                System.out.println(sb);
                return;
            }

            parents = new int[M];

            for (int i = 1; i < M; i++) parents[i] = i;

            pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                ans += z;

                pq.add(new Node(x, y, z));
            }

            solve();

            ans = 0;
        }
    }

    static void solve() {
        int cnt = 0, sum = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (union(cur.x, cur.y)) {
                sum += cur.z;
                cnt++;

                if (cnt == M - 1) break;
            }
        }

        sb.append(ans - sum).append("\n");
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootA] = rootB;
            return true;
        }

        return false;
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