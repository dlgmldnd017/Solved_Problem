import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] color;
    static List<Integer>[] list;
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

            color = new int[N + 1]; // 0: 방문 안 함, 1: 색1, 2: 색2

            if (solve()) sb.append("possible\n");
            else sb.append("impossible\n");
        }

        System.out.println(sb);
    }

    static boolean solve() {
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                if (!bfs(i)) return false;
            }
        }
        return true;
    }

    static boolean bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        color[start] = 1; // 첫 번째 색 지정

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if (color[next] == 0) { // 방문하지 않은 경우
                    color[next] = 3 - color[cur]; // 1 -> 2, 2 -> 1
                    q.add(next);
                } else if (color[next] == color[cur]) { // 같은 색상이면 이분 그래프가 아님
                    return false;
                }
            }
        }
        return true;
    }
}
