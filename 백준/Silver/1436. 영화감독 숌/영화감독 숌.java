import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int num = 666, cnt = 1;

        while (cnt != N) {
            num++;
            if (String.valueOf(num).contains("666")) cnt++;
        }

        ans = num;
    }
}