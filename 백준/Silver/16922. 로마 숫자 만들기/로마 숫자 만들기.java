import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int N, ans;
    static int arr[] = {1, 5, 10, 50};
    static Set<Integer> set = new HashSet<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        solve(0, 0);

        System.out.println(ans);
    }

    static void solve(int depth, int idx) {
        if (depth == N) {
            int sum = 0;
            for (int j : list) sum += j;
            if (set.contains(sum)) return;

            set.add(sum);
            ans++;
            return;
        }

        for (int i = idx; i < 4; i++) {
            list.add(arr[i]);
            solve(depth + 1, i);
            list.remove(list.size() - 1);
        }
    }
}