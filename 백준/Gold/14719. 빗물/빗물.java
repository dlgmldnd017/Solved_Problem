import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, h[], ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        h = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) h[i] = Integer.parseInt(st.nextToken());

        if (W != 0) solve();

        System.out.println(ans);
    }

    static void solve() {
        int leftMax[] = new int[W];
        int rightMax[] = new int[W];

        leftMax[0] = h[0];

        for (int i = 1; i < W; i++) leftMax[i] = Math.max(leftMax[i - 1], h[i]);

        rightMax[W - 1] = h[W - 1];

        for (int i = W - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], h[i]);

        for (int i = 0; i < W; i++) ans += Math.min(leftMax[i], rightMax[i]) - h[i];
    }
}