import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, ans;
    static String str[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String tmp[] = new String[R];

        for (int i = 0; i < R; i++) tmp[i] = br.readLine();

        str = new String[C];

        for (int i = 0; i < C; i++) {
            str[i] = "";
            for (int j = 0; j < R; j++) str[i] += tmp[j].charAt(i);
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
            String tmp = str[i].substring(mid, R);

            if (set.contains(tmp)) return false;
            set.add(tmp);
        }

        return true;
    }
}