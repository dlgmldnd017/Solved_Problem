import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] crane;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        crane = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crane);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            list.add(-Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        if (crane[N - 1] < -list.get(0)) {
            ans = -1;
        } else {
            solve();
        }

        System.out.println(ans);
    }

    static void solve() {
        while (!list.isEmpty()) {
            int i = N - 1, j = 0;

            while (i >= 0 && j < list.size()) {
                if (crane[i] >= -list.get(j)) {
                    list.remove(j);
                    i--;
                } else {
                    j++;
                }
            }

            ans++;
        }
    }
}