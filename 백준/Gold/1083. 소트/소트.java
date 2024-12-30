import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, A[], S;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        S = Integer.parseInt(br.readLine());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0; i < N && 0 < S; i++) {
            int maxIdx = i;

            for (int j = i + 1; j < N && j <= i + S; j++) {
                if (A[j] > A[maxIdx]) maxIdx = j;
            }

            S -= (maxIdx - i);

            for (int j = maxIdx; j > i; j--) swap(j, j - 1);
        }

        for (int i = 0; i < N; i++) sb.append(A[i] + " ");
    }

    static void swap(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}