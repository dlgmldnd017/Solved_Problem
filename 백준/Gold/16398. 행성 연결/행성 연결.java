import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] cost;
    static long ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        boolean visited[] = new boolean[N];

        int[] arr = new int[N];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0;

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;

            int idx = 1;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && min > arr[j]) {
                    min = arr[j];
                    idx = j;
                }
            }

            visited[idx] = true;
            ans += min;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && cost[idx][j] < arr[j]) arr[j] = cost[idx][j];
            }
        }
    }
}