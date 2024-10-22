import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String S, P;
    static int ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = br.readLine();
        P = br.readLine();

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int j = 0;

        for (int i = 0; i < P.length(); i++) {
            if (S.indexOf(P.substring(j, i + 1)) != -1) continue;
            ans++;
            j = i;
        }
        ans++;
    }
}