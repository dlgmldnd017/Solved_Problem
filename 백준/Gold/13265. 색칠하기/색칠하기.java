import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] color;
    static List<Integer> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            list = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            color = new int[N + 1];

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                if (!bfs(i)) {
                    sb.append("impossible").append("\n");
                    return;
                }
            }
        }

        sb.append("possible").append("\n");
    }

    static boolean bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        color[start] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (color[next] == 0) {
                    color[next] = 3 - color[cur];
                    q.add(next);
                } else if (color[cur] == color[next]) return false;
            }
        }

        return true;
    }
}