import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static final int MID = 10001;
    static final int MAX = 20001;

    static int N;
    static int[] tree = new int[1 << 16]; // 65536

    static void update(int now, int s, int e, int idx, int v) {
        tree[now] += v;

        if (s == e) return;

        int m = (s + e) >>> 1;
        int l = now << 1, r = l | 1;

        if (idx <= m) update(l, s, m, idx, v);
        else update(r, m + 1, e, idx, v);
    }

    static int getMid(int now, int s, int e, int value) {
        if (s == e) return s;

        int m = (s + e) >>> 1;
        int l = now << 1, r = l | 1;

        if (value <= tree[l]) return getMid(l, s, m, value);
        else return getMid(r, m + 1, e, value - tree[l]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            update(1, 1, MAX, value + MID, 1);
            sb.append(getMid(1, 1, MAX, (i + 1) / 2) - MID).append('\n');
        }
        System.out.print(sb);
    }
}
