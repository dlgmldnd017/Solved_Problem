import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int s, v;

    Node(int s, int v) {
        this.s = s;
        this.v = v;
    }

    public int compareTo(Node n) {
        if (this.s == n.s) return this.v - n.v;
        return this.s - n.s;
    }
}

public class Main {
    static int N, ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.add(new Node(s, +1));
            list.add(new Node(e, -1));
        }

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int cnt = 0;

        for (Node cur : list) {
            cnt += cur.v;

            if(ans < cnt) ans = cnt;
        }
    }
}