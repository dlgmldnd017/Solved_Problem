import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dice;
    static int ans;
    static int[][] d = {
            {1, 2, 3, 4},
            {0, 2, 4, 5},
            {0, 1, 3, 5},
            {0, 2, 4, 5},
            {0, 1, 3, 5},
            {1, 2, 3, 4},
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(ans);
    }

    static void solve() {
        for (int i = 0; i < 6; i++) {
            selectDice(dice[0][i], i);
        }
    }

    static void selectDice(int num, int idx) {
        int sum = getMax(0, idx);

        for (int i = 1; i < N; i++) {
            int oppNum = -1;

            for (int j = 0; j < 6; j++) {
                if (dice[i][j] != num) continue;

                sum += getMax(i, j);
                oppNum = getOppNum(j);
                break;
            }

            num = dice[i][oppNum];
        }

        ans = Math.max(ans, sum);
    }

    // 옆면 기준으로 4개의 숫자중 합친 값이 가장 높은 값
    static int getMax(int diceNum, int idx) {
        int max = -1, maxIdx = -1;

        for (int i = 0; i < 4; i++) {
            if (max < dice[diceNum][d[idx][i]]) {
                max = dice[diceNum][d[idx][i]];
//                maxIdx = i;
            }
        }

//        int idx1;
//
//        if(maxIdx==0) idx1 = 3;
//        else idx1 = maxIdx-1;
//
//        int idx2;
//
//        if(maxIdx==3) idx2 = 0;
//        else idx2 = maxIdx+1;

        return max;
    }

    // 아랫면 또는 윗면 받아오기
    static int getOppNum(int idx) {
        switch (idx) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            default:
                return 0;
        }
    }
}