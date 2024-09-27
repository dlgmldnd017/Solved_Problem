import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int rank, A, T;

    Node(int rank, int A, int T) {
        this.rank = rank;
        this.A = A;
        this.T = T;
    }

    public int compareTo(Node n) {
        return n.rank - rank;
    }
}

public class Main {
    static int N, ans;
    static List<Node> list = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());

            if (C == 0) {
                list.add(new Node(i, 0, 0));
            } else {
                int A = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                list.add(new Node(i, A, T));
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            Node cur = list.get(i);
//            System.out.println(cur.rank + " : " + cur.A + " : " + cur.T);

            if (cur.A == 0) {
                if (pq.isEmpty()) continue;
                study();
            } else {
                pq.add(new Node(cur.rank, cur.A, cur.T));
                study();
            }
        }
    }

    static void study() {
        Node front = pq.poll();
//        System.out.println(front.rank + " : " + front.A + " : " + front.T);

        if (front.T - 1 == 0) ans += front.A;
        else pq.add(new Node(front.rank, front.A, front.T - 1));
    }
}

