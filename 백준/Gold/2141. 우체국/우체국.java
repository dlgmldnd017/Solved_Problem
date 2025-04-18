import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long ans;
    static long[][] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new long[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Long.parseLong(st.nextToken());
            arr[i][1] = Long.parseLong(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        long sum = 0;

        for (int i = 0; i < N; i++) sum += arr[i][1];

        long people = 0;

        for (int i = 0; i < N; i++) {
            people += arr[i][1];
            if (people >= (sum + 1) / 2) {
                ans = arr[i][0];
                return;
            }
        }
    }
}
