import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, C, ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        ans = solve();

        System.out.println(ans);
    }

    static int solve() {
        int low = 1, high = list.get(list.size() - 1);

        while (low <= high) {
            int mid = (low + high) / 2;

            if (getInstallRouter(mid) < C) high = mid - 1;
            else low = mid + 1;
        }

        return low - 1;
    }

    static int getInstallRouter(int mid) {
        int start = 0, cnt = 1;

        for (int i = 1; i < N; i++) {
            if (list.get(i) - list.get(start) >= mid) {
                start = i;
                cnt++;
            }
        }
        return cnt;
    }
}