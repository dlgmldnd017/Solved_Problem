import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));

        solve(0);

        System.out.println(ans);
    }

    static void solve(int sum) {
        if (list.size() == 2) {
            if (ans < sum) ans = sum;
            return;
        }

        for (int i = 1; i < list.size() - 1; i++) {
            int tmp = list.get(i);
            int value = list.get(i - 1) * list.get(i + 1);
            list.remove(i);
            solve(sum + value);
            list.add(i, tmp);
        }
    }
}