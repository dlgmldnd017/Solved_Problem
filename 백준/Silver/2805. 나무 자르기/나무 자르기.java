import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[];
    static long ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long low = 0, high = arr[N - 1];

        while (low <= high) {
            long mid = (low + high) / 2;

            if (getTree(mid) >= M) {
                if (ans < mid) ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }

    static long getTree(long H) {
        long cnt = 0;

        for(int i=0; i<N; i++) {
            if(arr[i]>H) cnt += arr[i]-H;
        }

        return cnt;
    }
}