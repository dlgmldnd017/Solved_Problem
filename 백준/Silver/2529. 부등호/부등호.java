import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static String arr[], map[], max, min;
    static List<String> list = new ArrayList<>();
    static boolean vistied[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        map = new String[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) map[i] = st.nextToken();

        arr = new String[10];

        for (int i = 0; i < 10; i++) arr[i] = i + "";

        max = "-9999999999";
        min = "9999999999";

        for (int i = 0; i < 10; i++) {
            vistied = new boolean[10];
            vistied[i] = true;
            solve(0, i, i + "");
        }

        System.out.println(list.get(list.size() - 1) + "\n" + list.get(0));
    }

    static void solve(int depth, int left, String word) {
        if (depth == K) {
            list.add(word);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (vistied[i]) continue;

            String str = map[depth];

            if(str.equals(">")){
                if (left > i) {
                    vistied[i] = true;
                    solve(depth + 1, i, word + i);
                    vistied[i] = false;
                }
            } else {
                if (left < i) {
                    vistied[i] = true;
                    solve(depth + 1, i, word + i);
                    vistied[i] = false;
                }
            }
        }
    }
}