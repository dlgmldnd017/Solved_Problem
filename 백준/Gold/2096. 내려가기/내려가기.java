import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] maxDP = new int[3];
        int[] minDP = new int[3];

        int[] maxT = new int[3];
        int[] minT = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            maxDP[i] = minDP[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            maxT[0] = a + Math.max(maxDP[0], maxDP[1]);
            maxT[1] = b + Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
            maxT[2] = c + Math.max(maxDP[1], maxDP[2]);

            minT[0] = a + Math.min(minDP[0], minDP[1]);
            minT[1] = b + Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
            minT[2] = c + Math.min(minDP[1], minDP[2]);

            for (int j = 0; j < 3; j++) {
                maxDP[j] = maxT[j];
                minDP[j] = minT[j];
            }
        }

        int maxScore = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
        int minScore = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);

        System.out.println(maxScore + " " + minScore);
    }
}