import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, A[][], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 1; i < N; i++) {
            A[i][0] += Math.min(A[i-1][1], A[i-1][2]);
            A[i][1] += Math.min(A[i-1][0], A[i-1][2]);
            A[i][2] += Math.min(A[i-1][0], A[i-1][1]);
        }

        ans = Math.min(A[N-1][0], Math.min(A[N-1][1], A[N-1][2]));
    }
}

