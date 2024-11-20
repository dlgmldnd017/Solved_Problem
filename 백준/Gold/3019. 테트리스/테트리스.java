import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int C, P, arr[], ans;

    static String info[][] = {
            {},
            {"0000"},
            {"00"},
            {"001", "10"},
            {"100", "01"},
            {"000", "01", "101", "10"},
            {"000", "00", "011", "20"},
            {"000", "02", "110", "00"}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        arr = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (P==1) ans += C;

        for (int i = 0; i < info[P].length; i++) {
            check(info[P][i]);
        }
    }

    static void check(String block) {
        for (int i = 0; i <= C - block.length(); i++) {
            int gap = arr[i] - (block.charAt(0) - '0');

            boolean flag = true;

            for (int j = 1; j < block.length(); j++) {
                if (arr[i + j] - (block.charAt(j) - '0') != gap) {
                    flag = false;
                    break;
                }
            }

            if (flag) ans++;
        }
    }
}