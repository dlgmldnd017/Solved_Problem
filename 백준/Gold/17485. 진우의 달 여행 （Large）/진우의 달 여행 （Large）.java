import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int y, x, cost, dir;

    Node(int y, int x, int cost, int dir) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.dir = dir;
    }

    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}

public class Main {
    static int N, M, map[][], dp[][][], ans;
    static int dx[] = {-1, 0, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) Arrays.fill(dp[i][j], map[i][j]);
                else Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 왼쪽에서 올 경우 (0)
                if (j != 0) dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + map[i][j];
                
                // 중앙에서 올 경우 (1)
                dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j];
                
                // 오른쪽에서 올 경우 (2)
                if (j != M - 1) dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + map[i][j];
            }
        }

        for (int j = 0; j < M; j++) {
            int min = dp[N - 1][j][0];

            for (int k = 1; k < 3; k++) min = Math.min(min, dp[N - 1][j][k]);

            if (ans > min) ans = min;
        }
    }
}