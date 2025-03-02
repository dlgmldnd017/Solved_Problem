import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int len, ans;
    static String input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        len = input.length();

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int cntA = 0;

        for (char c : input.toCharArray()) {
            if (c == 'a') cntA++;
        }

        if (cntA == 0 || cntA == len) return;


        String str = input + input;
        int cntB = 0;

        for (int i = 0; i < cntA; i++) {
            if (str.charAt(i) == 'b') cntB++;
        }

        ans = cntB;

        for (int i = 0; i < len - 1; i++) {
            if (str.charAt(i) == 'b') cntB--;
            if (str.charAt(i + cntA) == 'b') cntB++;
            if (ans > cntB) ans = cntB;
        }
    }
}