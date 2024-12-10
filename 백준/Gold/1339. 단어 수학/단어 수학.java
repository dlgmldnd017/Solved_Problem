import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[26];

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);

                arr[c - 'A'] += (int) Math.pow(10, input.length() - j - 1);
            }
        }

        Arrays.sort(arr);

        solve();

        System.out.println(ans);
    }

    static void solve() {
        int num = 9, idx = arr.length - 1;

        while (idx >= 0 && arr[idx] != 0) ans += num-- * arr[idx--];
    }
}