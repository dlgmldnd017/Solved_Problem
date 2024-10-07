import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long city[], stationCost[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        city = new long[N - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            city[i] = Long.parseLong(st.nextToken());
        }

        stationCost = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stationCost[i] = Long.parseLong(st.nextToken());
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        long min = stationCost[0];

        for (int i = 0; i < city.length; i++) {
            if (stationCost[i] < min) min = stationCost[i];

            ans += (min * city[i]);
        }
    }
}