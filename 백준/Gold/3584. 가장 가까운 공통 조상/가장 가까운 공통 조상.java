import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int T, N, A, B;
    static int[] p, h;
    static List<Integer> list[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            p = new int[N + 1];

            list = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                p[b] = a;
                list[a].add(b);
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        int root = -1;

        for (int i = 1; i <= N; i++) {
            if (p[i] == 0) {
                root = i;
            }
        }

        h = new int[N + 1];

        calcHeight(root, 0);

        if (h[A] < h[B]) {
            do B = p[B];
            while (h[B] != h[A]);
        } else if (h[A] > h[B]) {
            do A = p[A];
            while (h[A] != h[B]);
        }

        if (A == B) {
            sb.append(A).append("\n");
            return;
        }

        while (p[A] != p[B]) {
            A = p[A];
            B = p[B];
        }

        sb.append(p[A]).append("\n");
    }

    static void calcHeight(int cur, int num) {
        h[cur] = num;

        for (int next : list[cur]) calcHeight(next, num + 1);
    }
}