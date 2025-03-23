import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, min, max;
    static char[][] ch;

    static int[] dy = {0, 1};
    static int[] dx = {1, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ch = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) ch[i][j] = st.nextToken().charAt(0);
        }

        solve();

        System.out.println(max + " " + min);
    }

    static void solve() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        dfs(0, 0, ch[0][0] - '0', ' ');
    }

    static void dfs(int y, int x, int currentValue, char lastOperator) {
        if (y == N - 1 && x == N - 1) {
            if (max < currentValue) max = currentValue;
            if (min > currentValue) min = currentValue;
            return;
        }

        for (int k = 0; k < 2; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (!inRange(ny, nx)) continue;

            char next = ch[ny][nx];

            if (Character.isDigit(next)) {
                int value = calc(currentValue, next - '0', lastOperator);
                dfs(ny ,nx, value, lastOperator);
            } else {
                dfs(ny ,nx, currentValue, next);
            }
        }
    }

    static boolean inRange(int y, int x) {
        return (0 <= y && y < N) && (0 <= x && x < N);
    }

    static int calc(int a, int b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: return b;
        }
    }
}