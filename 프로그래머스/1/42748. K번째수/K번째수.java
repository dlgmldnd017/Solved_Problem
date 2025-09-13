import java.util.*;

class Solution {
    // 펜윅트리 (값 범위 기준)
    static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int i, int delta) {
            while (i <= n) {
                tree[i] += delta;
                i += (i & -i);
            }
        }

        int sum(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= (i & -i);
            }
            return res;
        }

        int rangeSum(int l, int r) {
            return sum(r) - sum(l - 1);
        }
    }

    public int[] solution(int[] array, int[][] commands) {
        int MAXV = 100; // 값 범위 1~100
        int n = array.length;
        int m = commands.length;
        int[] answer = new int[m];

        // prefix Fenwick: 각 prefix마다 Fenwick 트리를 하나 만든다고 가정
        Fenwick[] prefix = new Fenwick[n + 1];
        prefix[0] = new Fenwick(MAXV);
        for (int i = 1; i <= n; i++) {
            prefix[i] = new Fenwick(MAXV);
            // 이전 prefix 복사
            for (int v = 1; v <= MAXV; v++) {
                prefix[i].tree[v] = prefix[i - 1].tree[v];
            }
            prefix[i].update(array[i - 1], 1);
        }

        for (int qi = 0; qi < m; qi++) {
            int i = commands[qi][0];
            int j = commands[qi][1];
            int k = commands[qi][2];

            // 이분 탐색으로 k번째 수 찾기
            int lo = 1, hi = MAXV, res = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                int cnt = prefix[j].sum(mid) - prefix[i - 1].sum(mid);
                if (cnt >= k) {
                    res = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            answer[qi] = res;
        }
        return answer;
    }
}
