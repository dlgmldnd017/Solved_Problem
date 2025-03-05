import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C, P, ans;
    static int[] arr;
    static String[][] blocks = {
            {},
            {"0000"},
            {"00"},
            {"001", "10"},
            {"100", "01"},
            {"000", "01", "101", "10"},
            {"000", "00", "011", "20"},
            {"000", "00", "110", "02"}
    };

    public static void main(String args[]) throws Exception {
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
        if (P == 1) ans += C;

        for (int i = 0; i < blocks[P].length; i++) checkBlockMatch(blocks[P][i]);
    }

    static void checkBlockMatch(String block) {
        for (int i = 0; i <= C - block.length(); i++) {
            int diff1 = arr[i] - (block.charAt(0) - '0');
            boolean check = false;

            for (int j = 1; j < block.length(); j++) {
                int diff2 = arr[i + j] - (block.charAt(j) - '0');

                if (diff1 != diff2) {
                    check = false;
                    break;
                }

                check = true;
            }

            if (check) ans++;
        }
    }
}