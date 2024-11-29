import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static List<Integer> list[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        solve(start, end);

        if (ans == 0) ans = -1;
        System.out.println(ans);
    }

    static void solve(int start, int end) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 0));

        boolean visited[] = new boolean[N + 1];

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visited[cur.e]) continue;
            visited[cur.e] = true;

            for (int next : list[cur.e]) {
                if (visited[next]) continue;

                if (next == end) {
                    ans = cur.cnt + 1;
                    return;
                }

                q.add(new Node(next, cur.cnt + 1));
            }
        }
    }
}

class Node {
    int e, cnt;

    Node(int e, int cnt) {
        this.e = e;
        this.cnt = cnt;
    }
}