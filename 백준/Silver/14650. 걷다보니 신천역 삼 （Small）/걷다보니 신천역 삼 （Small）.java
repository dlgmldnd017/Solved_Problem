import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[] = {2, 1, 0}, ans;
    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        solve(0);

        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == N) {
            if (list.get(0) == 0) return;
            String str = "";
            for (int j : list) str += j;
            int result = Integer.parseInt(str);

            if (result != 0 && result % 3 == 0) ans++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            list.add(arr[i]);
            solve(depth + 1);
            list.remove(list.size() - 1);
        }
    }
}