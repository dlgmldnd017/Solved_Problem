import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, ans;
    static char ch[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ch = new char[C][R];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();

            for (int j = 0; j < C; j++) ch[j][i] = input.charAt(j);
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int low = 0, high = R;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (check(mid)) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
    }

    static boolean check(int mid) {
        Set<String> set = new TreeSet<>();

        for (int i = 0; i < C; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = mid; j < R; j++) sb.append(ch[i][j]);

            if (set.contains(sb.toString())) return false;
            set.add(sb.toString());
        }

        return true;
    }
}