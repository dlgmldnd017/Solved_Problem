import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] ch;
    static int N, M;
    static long[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ch = new char[N * 2][M * 2];
        arr = new long[N * 2][M * 2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                ch[i][j] = ch[i + N][j] = ch[i][j + M] = ch[i + N][j + M] = c;
            }
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0; i < N * 2; i++) {
            for (int j = 0; j < M * 2; j++) arr[i][j] = (long) (i + 1) * (j + 1) * (N * 2 - i) * (M * 2 - j);
        }

        long tmp[] = new long[26];

        for (int i = 0; i < N * 2; i++) {
            for (int j = 0; j < M * 2; j++) tmp[ch[i][j] - 'A'] += arr[i][j];
        }

        for (long l : tmp) sb.append(l).append("\n");
    }
}