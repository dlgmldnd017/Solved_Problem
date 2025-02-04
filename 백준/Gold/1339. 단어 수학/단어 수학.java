import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[26];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                arr[input.charAt(j) - 'A'] += (int) Math.pow(10, input.length() - j - 1);
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Arrays.sort(arr);

        int num = 9, len = arr.length - 1;

        while (len >= 0 && arr[len] != 0) ans += num-- * arr[len--];
    }
}