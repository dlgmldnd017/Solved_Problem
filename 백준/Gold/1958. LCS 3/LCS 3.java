import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int ans;
    static String str1, str2, str3;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();
        str3 = br.readLine();

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int len1 = str1.length() + 1;
        int len2 = str2.length() + 1;
        int len3 = str3.length() + 1;

        int[][][] dp = new int[len1][len2][len3];

        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                for (int k = 1; k < len3; k++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    else dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                }
            }
        }

        ans = dp[len1 - 1][len2 - 1][len3 - 1];
    }
}