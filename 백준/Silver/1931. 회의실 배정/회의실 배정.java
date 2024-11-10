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
        if (this.e == n.e) return this.s - n.s;
        return this.e - n.e;
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

            list.add(new Node(s, e));
        }

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int prevE = -1;

        for (Node cur : list) {
            if (cur.s < prevE) continue;

            prevE = cur.e;
            ans++;
        }
    }
}