import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N = 10, arr[], cnt[], ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == 10) {
            int cnt = 0;
            for (int i = 0; i < list.size(); i++) if (arr[i] == list.get(i)) cnt++;

            if (cnt >= 5) ans++;

            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (depth > 1 && list.get(depth - 2) == list.get(depth - 1) && list.get(depth - 1) == i) continue;
            list.add(i);
            solve(depth + 1);
            list.remove(list.size() - 1);
        }
    }
}