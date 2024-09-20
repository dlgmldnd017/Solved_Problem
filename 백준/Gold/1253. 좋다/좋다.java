import java.util.*;
import java.io.*;

public class Main {
    static int N, A[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int k = 0; k < N; k++) {
            int target = A[k];
            int i = 0;
            int j = N - 1;

            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }

                int sum = A[i] + A[j];

                if (sum == target) {
                    ans++;
                    break;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
    }
}
