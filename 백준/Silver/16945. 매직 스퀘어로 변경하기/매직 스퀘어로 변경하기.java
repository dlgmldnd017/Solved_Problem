import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[][], ans;
    static boolean visited[] = new boolean[10];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = 3;

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = Integer.MAX_VALUE;

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int cost) {
        if (depth == 9 && isMagicSquare()) {
            if (ans > cost) ans = cost;
            return;
        }

        int y = depth / 3;
        int x = depth % 3;

        for (int i = 1; i <= 9; i++) {
            if (visited[i]) continue;

            int tmp = arr[y][x];
            arr[y][x] = i;
            visited[i] = true;
            solve(depth + 1, cost + Math.abs(tmp - i));
            visited[i] = false;
            arr[y][x] = tmp;
        }
    }

    static boolean isMagicSquare() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int tmp1 = 0;
            int tmp2 = 0;

            for (int j = 0; j < N; j++) {
                tmp1 += arr[i][j];
                tmp2 += arr[j][i];
            }

            if (sum == 0) {
                if (tmp1 == tmp2) sum = tmp1;
                else return false;
            } else if (sum != tmp1 || sum != tmp2) return false;
        }

        int diaS1 = arr[0][0] + arr[1][1] + arr[2][2];
        int diaS2 = arr[2][0] + arr[1][1] + arr[0][2];

        if (diaS1 != sum || diaS2 != sum) return false;

        return true;
    }
}