import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int T, S;

    Node(int T, int S) {
        this.T = T;
        this.S = S;
    }

    public int compareTo(Node n) {
        if (this.S == n.S) return this.T - n.T;
        return this.S - n.S;
    }
}

public class Main {
    static int N, ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            list.add(new Node(T, S));
        }

        Collections.sort(list);

        ans = Integer.MIN_VALUE;

        solve();

        if (ans == Integer.MIN_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i <= list.get(0).S - list.get(0).T; i++) {
            if (start(i)) ans = i;
        }
    }

    static boolean start(int i) {
        for (Node n : list) {
            i += n.T;

            if(i>n.S) return false;
        }

        return true;
    }
}