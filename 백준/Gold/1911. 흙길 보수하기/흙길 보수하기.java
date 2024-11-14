import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int s, e;

    Node(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public int compareTo(Node n) {
        return this.s - n.s;
    }
}

public class Main {
    static int N, L, ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken()) - 1;

            list.add(new Node(s, e));
        }

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int prev = -1;

        for (Node cur : list) {
            if (cur.s < prev) cur.s = prev;

            while (cur.s <= cur.e) {
                cur.s += L;
                ans++;
            }

            prev = cur.s;
        }
    }
}
