import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, arr[][], ans;

    public static void main(String[] args) throws Exception {
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

        for (int i = 0; i < N; i++) {
            if (solve(arr[i])) ans++;
            if (solve(getColumn(i))) ans++;
        }

        System.out.println(ans);
    }

    static boolean solve(int line[]) {
        boolean visited[] = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i] - line[i + 1];

            if (diff == 0) continue;

            if (Math.abs(diff) > 1) return false;

            if (diff == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j == N || line[j] != line[i + 1] || visited[j]) return false;
                    visited[j] = true;
                }
            } else {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || line[j] != line[i] || visited[j]) return false;
                    visited[j] = true;
                }
            }
        }
        return true;
    }

    static int[] getColumn(int i) {
        int tmp[] = new int[N];

        for (int j = 0; j < N; j++) tmp[j] = arr[j][i];

        return tmp;
    }
}