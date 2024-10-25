import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int T, S;

    public Node(int T, int S) {
        this.T = T;
        this.S = S;
    }

    public int compareTo(Node n) {
        if (this.S == n.S) return this.T - n.T;
        return this.S - n.S;
    }
}

public class Main {
    static int N, T, S, ans;
    static List<Node> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            list.add(new Node(T, S));
        }

        Collections.sort(list);

        ans = Integer.MIN_VALUE;

        solve();

        if (ans == Integer.MIN_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < list.get(list.size() - 1).S; i++) {
            int nowTime = i;

            for (Node cur : list) {
                nowTime += cur.T;

                if (nowTime <= cur.S) continue;
                return;
            }

            ans = i;
        }
    }
}