import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] str;
    static char[] code;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(sc.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        code = new char[L];

        str = new char[C];

        st = new StringTokenizer(sc.readLine());
        for (int i = 0; i < C; i++) str[i] = st.nextToken().charAt(0);

        Arrays.sort(str);

        solve(0, 0);

        System.out.println(sb);
    }

    static void solve(int depth, int idx) {
        if (depth == L) {
            if (isValid()) sb.append(code).append("\n");
            return;
        }

        for (int i = idx; i < C; i++) {
            code[depth] = str[i];
            solve(depth + 1, i + 1);
        }
    }

    static boolean isValid() {
        int mo = 0;
        int ja = 0;

        for (char c : code) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') mo++;
            else ja++;
        }

        if (mo >= 1 && ja >= 2) {
            return true;
        }
        return false;
    }
}