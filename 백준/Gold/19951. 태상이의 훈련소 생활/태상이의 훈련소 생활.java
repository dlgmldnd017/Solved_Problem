import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] H, arr;

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        H = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        arr = new int[N + 2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            arr[a] += k;
            arr[b + 1] += -k;
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            cnt += arr[i];
            H[i] += cnt;
            sb.append(H[i]).append(" ");
        }
    }
}