import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][], ans;
    static boolean visited[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];

        ans = Integer.MAX_VALUE;

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (ans == 0) return;

        if (depth == N) {
            int sum1 = 0, sum2 = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (visited[i] != visited[j]) continue;

                    if (visited[i]) sum1 += arr[i][j] + arr[j][i];
                    else sum2 += arr[i][j] + arr[j][i];
                }
            }

            int diff = Math.abs(sum1 - sum2);

            if (ans > diff) ans = diff;
            return;
        }

        visited[depth] = true;
        solve(depth + 1);

        visited[depth] = false;
        solve(depth + 1);
    }
}