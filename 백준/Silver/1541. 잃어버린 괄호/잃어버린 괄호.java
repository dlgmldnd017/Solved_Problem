import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int ans;
    static String input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine();

        ans = Integer.MAX_VALUE;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        StringTokenizer st = new StringTokenizer(input, "-");

        while(st.hasMoreTokens()) {
            int sum = 0;

            StringTokenizer st2 = new StringTokenizer(st.nextToken(), "+");

            while(st2.hasMoreTokens()) sum += Integer.parseInt(st2.nextToken());

            if(ans == Integer.MAX_VALUE) ans = sum;
            else ans -= sum;
        }
    }
}