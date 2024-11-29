import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int F, S, G, U, D, ans;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new boolean[F + 1];

        solve();

        if (S != G && ans == 0) System.out.println("use the stairs");
        else System.out.println(ans);
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.stair == G) {
                ans = cur.cnt;
                return;
            }

            if (visited[cur.stair]) continue;
            visited[cur.stair] = true;

            if (cur.stair + U <= F) pq.add(new Node(cur.stair + U, cur.cnt + 1));
            if (D != 0 && cur.stair - D >= 1) pq.add(new Node(cur.stair - D, cur.cnt + 1));
        }
    }
}

class Node implements Comparable<Node> {
    int stair, cnt;

    Node(int stair, int cnt) {
        this.stair = stair;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        return this.cnt - n.cnt;
    }
}