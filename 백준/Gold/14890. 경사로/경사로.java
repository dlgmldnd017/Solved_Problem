import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L, ans;
    static int[][] arr;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            if (canGo(arr[i])) ans++;
            if (canGo(getCol(i))) ans++;
        }
    }

    static boolean canGo(int[] way) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = way[i] - way[i + 1];

            if (Math.abs(diff) > 1) return false;

            if (diff == 0) continue;

            if (diff > 0) {
                if (visited[i + 1]) return false;
                visited[i + 1] = true;

                for (int j = i + 1; j < i + L; j++) {
                    if (j + 1 >= N || way[j] != way[j + 1] || visited[j + 1]) return false;
                    visited[j + 1] = true;
                }
            } else {
                if (visited[i]) return false;
                visited[i] = true;

                for (int j = i; j > i - L + 1; j--) {
                    if (j - 1 < 0 || way[j] != way[j - 1] || visited[j - 1]) return false;
                    visited[j - 1] = true;
                }
            }
        }

        return true;
    }

    static int[] getCol(int j) {
        int[] tmp = new int[N];

        for (int i = 0; i < N; i++) tmp[i] = arr[i][j];

        return tmp;
    }
}