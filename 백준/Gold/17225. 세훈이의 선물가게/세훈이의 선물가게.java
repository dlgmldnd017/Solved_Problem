import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A, B, N;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken()) - 1;

            pq.add(new Node(t, c, cnt));
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int i = 1, cntA = 0, cntB = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.c == 'B') {
                cntA++;
                list1.add(i++);
                if (cur.cnt != 0) pq.add(new Node(cur.t + A, cur.c, cur.cnt - 1));
            } else {
                cntB++;
                list2.add(i++);
                if (cur.cnt != 0) pq.add(new Node(cur.t + B, cur.c, cur.cnt - 1));
            }
        }

        sb.append(cntA + "\n");
        for (int j : list1) sb.append(j + " ");

        sb.append("\n");

        sb.append(cntB + "\n");
        for (int j : list2) sb.append(j + " ");
    }
}

class Node implements Comparable<Node> {
    int t, cnt;
    char c;

    Node(int t, char c, int cnt) {
        this.t = t;
        this.c = c;
        this.cnt = cnt;
    }

    public int compareTo(Node n) {
        if (this.t == n.t) return this.c - n.t;
        return this.t - n.t;
    }
}