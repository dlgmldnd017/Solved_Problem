import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(arr);

        boolean[][][] visited = new boolean[61][61][61];
        visited[arr[0]][arr[1]][arr[2]] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                int a = cur[0], b = cur[1], c = cur[2];

                if (a <= 0 && b <= 0 && c <= 0) {
                    ans = cnt;
                    return;
                }

                for (int[] attack : new int[][]{{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}}) {
                    int na = Math.max(0, a - attack[0]);
                    int nb = Math.max(0, b - attack[1]);
                    int nc = Math.max(0, c - attack[2]);

                    if (!visited[na][nb][nc]) {
                        visited[na][nb][nc] = true;
                        q.add(new int[]{na, nb, nc});
                    }
                }
            }

            cnt++;
        }
    }
}