import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int ans;
    static char[] ch;
    static char[][] map;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        ch = new char[input.length()];

        for (int i = 0; i < input.length(); i++) ch[i] = input.charAt(i);

        input = br.readLine();

        map = new char[2][input.length()];

        for (int i = 0; i < input.length(); i++) map[0][i] = input.charAt(i);

        input = br.readLine();

        for (int i = 0; i < input.length(); i++) map[1][i] = input.charAt(i);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int[][][] dp = new int[ch.length + 1][2][map[0].length + 1];

        // 첫 번째 두루마리 문자 작업
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < map[0].length; i++) {
                if (map[t][i] == ch[0]) dp[1][t][i + 1] = 1;
            }
        }

        // 전이 작업
        for (int c = 1; c < ch.length; c++) {
            for (int t = 0; t < 2; t++) {
                for (int i = c; i < map[0].length; i++) {
                    if (map[t][i] == ch[c]) {
                        for (int j = 0; j < i; j++) dp[c + 1][t][i + 1] += dp[c][1 - t][j + 1];
                    }
                }
            }
        }

        // 모든 경우의 합산
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i <= map[0].length; i++) ans += dp[ch.length][t][i];
        }
    }
}