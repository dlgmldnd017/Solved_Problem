import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, maxIdx, ans;
    static boolean[] visited;
    static List<Node> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, w));
            list[b].add(new Node(a, w));
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

        ans = 0;

        dfs(maxIdx, 0);
    }

    static void dfs(int cur, int sum) {
        if (ans < sum) {
            ans = sum;
            maxIdx = cur;
        }

        for (Node next : list[cur]) {
            if (visited[next.e]) continue;

            visited[next.e] = true;
            dfs(next.e, sum + next.w);
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