import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, idx;
    static long K;
    static List<Integer> list = new ArrayList<>();
    static String ans = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        solve(0);

        if(ans.equals("")) ans = "-1";
        System.out.println(ans);
    }

    static void solve(int sum) {
        if (sum > N) return;

        if (sum == N) {
            idx++;

            if (idx == K) {
                for (int i = 0; i < list.size() - 1; i++) {
                    ans += list.get(i) + "+";
                }
                ans += list.get(list.size() - 1);
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            list.add(i);
            solve(sum + i);
            list.remove(list.size() - 1);
        }
    }
}