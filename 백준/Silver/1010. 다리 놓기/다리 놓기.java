import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T, dp[][];

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[30][30];

            sb.append(solve(M, N) + "\n");
        }

        System.out.println(sb);
    }

    static int solve(int i, int j) {
        if (dp[i][j] > 0) return dp[i][j];

        if (i == j || j == 0) return dp[i][j] = 1;

        return dp[i][j] = solve(i - 1, j - 1) + solve(i - 1, j);
    }
}