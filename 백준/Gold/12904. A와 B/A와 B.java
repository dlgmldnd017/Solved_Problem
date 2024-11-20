import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());

        solve();

        System.out.println(T.toString().equals(S.toString()) ? 1 : 0);
    }

    static void solve() {

        while (S.length() < T.length()) {

            if (T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);

            } else {
                T.deleteCharAt(T.length() - 1);
                T.reverse();

            }
        }
    }
}