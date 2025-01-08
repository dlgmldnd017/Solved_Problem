import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, maxIdx, ans;
    static List<Node> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[y].add(new Node(x, w));
            list[x].add(new Node(y, w));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[N + 1];
        visited[maxIdx] = true;
        dfs(maxIdx, 0);
    }

    static void dfs(int idx, int w) {
        if (ans < w) {
            ans = w;
            maxIdx = idx;
        }

        for (Node n : list[idx]) {
            if (visited[n.e]) continue;

            visited[n.e] = true;
            dfs(n.e, w + n.w);
        }
    }
}

class Node {
    int e, w;

    Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}