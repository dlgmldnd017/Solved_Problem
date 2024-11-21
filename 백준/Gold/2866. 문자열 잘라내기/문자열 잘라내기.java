import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, ans;
    static StringBuilder sb[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sb = new StringBuilder[C];

        for (int i = 0; i < C; i++) sb[i] = new StringBuilder();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();

            for (int j = 0; j < C; j++) sb[j].append(str.charAt(j));
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < R - 1; i++) {
            for (int j = 0; j < C; j++) sb[j].deleteCharAt(0);

            TreeSet<String> set = new TreeSet<>();

            for (int j = 0; j < C; j++) {
                if (set.contains(sb[j].toString())) return;
                set.add(sb[j].toString());
            }

            ans++;
        }
    }
}