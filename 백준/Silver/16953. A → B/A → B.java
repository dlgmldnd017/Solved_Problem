import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solve(A, 1);

        System.out.println(-1);
    }

    static void solve(long cur, int cnt) {
        if (cur == B) {
            System.out.println(cnt);
            System.exit(0);
        }

        if (cur > B) return;

        solve(cur * 2, cnt + 1);

        String str = cur + "1";

        solve(Long.parseLong(str), cnt + 1);
    }
}