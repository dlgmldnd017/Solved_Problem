import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long[] fac;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        fac = new long[N + 1];

        fac[0] = 1;

        for (int i = 1; i <= N; i++) fac[i] = fac[i - 1] * i;

        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        if (num == 1) {
            long k = Long.parseLong(st.nextToken());

            for (int i = 0; i < N; i++) {
                long cnt = fac[N - 1 - i];

                for (int j = 1; j <= N; j++) {
                    if (visited[j]) continue;

                    if (cnt < k) k -= cnt;
                    else {
                        sb.append(j).append(" ");
                        visited[j] = true;
                        break;
                    }
                }
            }
        } else {
            int[] arr = new int[N];
            long k = 1;

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                int cnt = 0;

                for (int j = 1; j < arr[i]; j++) {
                    if (!visited[j]) cnt++;
                }

                visited[arr[i]] = true;
                k += cnt * fac[N - 1 - i];
            }

            sb.append(k);
        }

        System.out.println(sb);
    }
}