import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int C, P, h[], ans;
    static String blockInfo[][] = {
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

        h = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) h[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);
    }

    static void solve() {
        if (P == 1) ans += C;

        for (int i = 0; i < blockInfo[P].length; i++) checkEnableBlock(blockInfo[P][i]);
    }

    static void checkEnableBlock(String block) {
        for (int i = 0; i <= C - block.length(); i++) {
            int gap = h[i] - (block.charAt(0) - '0');

            boolean isSame = true;

            for (int j = 1; j < block.length(); j++) {
                if (h[i + j] - (block.charAt(j) - '0') != gap) {
                    isSame = false;
                    break;
                }
            }

            if (isSame) ans++;
        }
    }
}