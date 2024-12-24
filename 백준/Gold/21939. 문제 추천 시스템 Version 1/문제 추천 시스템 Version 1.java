import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N, M;
    static TreeSet<Integer> ts[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ts = new TreeSet[101];

        for (int i = 1; i <= 100; i++) ts[i] = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            ts[L].add(P);
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x, P, L;

            switch (command) {
                case "recommend":
                    x = Integer.parseInt(st.nextToken());
                    recommend(x);
                    break;

                case "add":
                    P = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());
                    add(P, L);
                    break;

                case "solved":
                    P = Integer.parseInt(st.nextToken());
                    solved(P);
                    break;
            }
        }

        System.out.println(sb);
    }

    static void recommend(int x) {
        if (x == 1) {
            for (int i = 100; i > 0; i--) {
                if (ts[i].isEmpty()) continue;
                sb.append(ts[i].last() + "\n");
                return;
            }
        } else {
            for (int i = 1; i <= 100; i++) {
                if (ts[i].isEmpty()) continue;
                sb.append(ts[i].first() + "\n");
                return;
            }
        }
    }

    static void add(int P, int L) {
        ts[L].add(P);
    }

    static void solved(int P) {
        for (int i = 1; i <= 100; i++) {
            if (!ts[i].contains(P)) continue;
            ts[i].remove(P);
        }
    }
}