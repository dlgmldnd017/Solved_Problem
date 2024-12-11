import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) break;

            int left = i + 1, right = N - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    int l = 1;
                    int r = 1;

                    if (arr[left] == arr[right]) {
                        int n = right - left + 1;
                        ans += getComb(n);
                        break;
                    }

                    while (arr[left] == arr[left + 1]) {
                        l++;
                        left++;
                    }

                    while (arr[right] == arr[right - 1]) {
                        r++;
                        right--;
                    }

                    ans += l * r;
                }

                if (sum > 0) right--;
                else left++;
            }
        }

        System.out.println(ans);
    }

    static long getComb(int n) {
        return n * (n - 1) / 2;
    }
}