import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, arr[][], cache[][], ans;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[2][N + 1];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j < N + 1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve();

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        cache = new int[2][N + 1];

        cache[0][1] = arr[0][1];
        cache[1][1] = arr[1][1];

        for (int j = 2; j < N + 1; j++) {
            cache[0][j] = Math.max(cache[1][j - 1], cache[1][j - 2]) + arr[0][j];
            cache[1][j] = Math.max(cache[0][j - 1], cache[0][j - 2]) + arr[1][j];
        }

        ans = Math.max(cache[0][N], cache[1][N]);
    }
}