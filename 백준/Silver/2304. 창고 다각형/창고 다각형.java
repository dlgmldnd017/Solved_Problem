import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int l, h;

    Node(int l, int h) {
        this.l = l;
        this.h = h;
    }
}

public class Main {
    static int N, arr[], maxL, maxH, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[1001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            arr[L] = H;

            if (maxH < H) {
                maxL = L;
                maxH = H;
            }
        }

        ans = maxH;

        solve();

        System.out.println(ans);
    }

    static void solve() {
        Deque<Node> dq = new LinkedList<>();

        for (int i = 0; i <= maxL; i++) {
            if (arr[i] == 0) continue;

            if (dq.isEmpty()) dq.addFirst(new Node(i, arr[i]));
            else if (dq.peekFirst().h <= arr[i]) {
                int nl = i - dq.peekFirst().l;
                int nh = dq.peekFirst().h;

                ans += nl * nh;
                dq.addFirst(new Node(i, arr[i]));
            }
        }

        dq.clear();

        for (int i = 1000; i >= maxL; i--) {
            if (arr[i] == 0) continue;

            if (dq.isEmpty()) dq.addFirst(new Node(i, arr[i]));
            else if (dq.peekFirst().h <= arr[i]) {
                int nl = dq.peekFirst().l - i;
                int nh = dq.peekFirst().h;

                ans += nl * nh;
                dq.addFirst(new Node(i, arr[i]));
            }
        }
    }
}