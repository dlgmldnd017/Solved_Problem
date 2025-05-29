import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] parents;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(u, v, w));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int selectedEdgeCount = 0;

        while (selectedEdgeCount < N - 2) {
            Node cur = pq.poll();

            if (union(cur.u, cur.v, parents)) {
                ans += cur.w;
                selectedEdgeCount++;
            }
        }
    }

    static int find(int x, int[] p) {
        if (p[x] == x) return x;

        return p[x] = find(p[x], p);
    }

    static boolean union(int a, int b, int[] parents) {
        int rootA = find(a, parents);
        int rootB = find(b, parents);

        if (rootA != rootB) {
            parents[rootB] = rootA;
            return true;
        }

        return false;
    }
}

class Node implements Comparable<Node> {
    int u, v, w;

    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int compareTo(Node n) {
        return this.w - n.w;
    }
}