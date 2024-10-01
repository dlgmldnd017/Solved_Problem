import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, jewCnt[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        jewCnt = new int[M];

        int right = -1;
        for (int i = 0; i < M; i++) {
            jewCnt[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, jewCnt[i]);
        }

        solve(1, right, 0);

        System.out.println(ans);
    }

    static void solve(int left, int right, int mid) {
        while (left <= right) {
            mid = (left + right) / 2;

            int cnt = 0;

            for (int i = 0; i < M; i++) {
                if (jewCnt[i] % mid == 0) cnt += jewCnt[i] / mid;
                else cnt += jewCnt[i] / mid + 1;
            }

            if (cnt > N) left = mid + 1;
            else {
                right = mid - 1;
                ans = mid;
            }
        }
    }
}

