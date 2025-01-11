import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, maxIdx, ans;
    static List<Tree> list[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Tree(b, w));
            list[b].add(new Tree(a, w));
        }

        if (N == 1) ans = 0;
        else solve();

        System.out.println(ans);
    }

    static void solve() {
        visited = new boolean[N + 1];
        dfs(1, 0);

        ans = 0;

        visited = new boolean[N + 1];
        dfs(maxIdx, 0);
    }

    static void dfs(int cur, int sum) {
        visited[cur] = true;

        if (ans < sum) {
            ans = sum;
            maxIdx = cur;
        }

        for (Tree next : list[cur]) {
            if (visited[next.e]) continue;
            dfs(next.e, sum + next.w);
        }
    }
}

class Tree {
    int e, w;

    Tree(int e, int w) {
        this.e = e;
        this.w = w;
    }
}