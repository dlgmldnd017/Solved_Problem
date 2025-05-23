import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.add(new Node(s, e));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Collections.sort(list, (a, b) -> a.s - b.s);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < list.size(); i++) {
            Node cur = list.get(i);

            if (!pq.isEmpty() && pq.peek() <= cur.s) pq.poll();

            pq.offer(cur.e);
        }

        ans = pq.size();
    }
}

class Node {
    int s, e;

    Node(int s, int e) {
        this.s = s;
        this.e = e;
    }
}