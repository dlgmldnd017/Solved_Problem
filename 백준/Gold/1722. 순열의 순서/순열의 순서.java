import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] per;
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

        int type = Integer.parseInt(st.nextToken());

        if (type == 1) {
            long k = Long.parseLong(st.nextToken());

            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                long count = fac[N - 1 - i];

                for (int j = 1; j <= N; j++) {
                    if (visited[j]) continue;
                    
                    if (count < k) {
                        k -= count;
                    } else {
                        list.add(j);
                        visited[j] = true;
                        break;
                    }
                }
            }

            for (int i : list) sb.append(i).append(" ");
        } else {
            per = new int[N];
            visited = new boolean[N + 1];
            long k = 1;

            for (int i = 0; i < N; i++) {
                per[i] = Integer.parseInt(st.nextToken());

                int count = 0;
                for (int j = 1; j < per[i]; j++) {
                    if (!visited[j]) count++;
                }

                k += count * fac[N - 1 - i];
                visited[per[i]] = true;
            }

            sb.append(k);
        }

        System.out.println(sb);
    }
}