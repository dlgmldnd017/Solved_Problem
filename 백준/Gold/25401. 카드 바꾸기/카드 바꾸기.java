import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        if (N != 2) solve();

        System.out.println(ans);
    }

    static void solve() {
        // 증가 개수
        int increaseCnt = getIncreaseCnt(arr.clone());
        int reverseCnt = getReverseCnt(arr.clone());
        int sameCnt = getSameCnt(arr.clone());

        ans = Math.min(increaseCnt, Math.min(reverseCnt, sameCnt));
    }

    static int getIncreaseCnt(int tmp[]) {
        int cnt = 0, increasing = tmp[1] - tmp[0];

        for (int i = 1; i < N - 1; i++) {
            int diff = tmp[i + 1] - tmp[i];

            if (diff == increasing) continue;

            tmp[i + 1] = tmp[i] + increasing;
            cnt++;
        }

        return cnt;
    }

    static int getReverseCnt(int tmp[]) {
        int cnt = 0, increasing = tmp[N - 1] - tmp[N - 2];

        for (int i = N - 2; i >= 1; i--) {
            int diff = tmp[i] - tmp[i - 1];

            if (diff == increasing) continue;

            tmp[i - 1] = tmp[i] - increasing;
            cnt++;
        }

        return cnt;
    }

    static int getSameCnt(int tmp[]) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int target = tmp[i], cnt = 0;

            for (int j = 0; j < N; j++) {
                if (i == j) continue;

                if (target != tmp[j]) cnt++;
            }

            min = Math.min(min, cnt);
        }

        return min;
    }
}


