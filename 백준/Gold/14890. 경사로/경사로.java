import java.io.*;
import java.util.*;

public class Main {
    static int N, L, arr[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if (isValidPath(arr[i])) ans++; // 가로 검사
            if (isValidPath(getColumn(i))) ans++; // 세로 검사
        }

        System.out.println(ans);
    }

    // 세로열 반환
    static int[] getColumn(int col) {
        int[] column = new int[N];
        for (int i = 0; i < N; i++) {
            column[i] = arr[i][col];
        }
        return column;
    }

    // 경사로를 설치할 수 있는지 확인
    static boolean isValidPath(int[] line) {
        boolean[] visited = new boolean[N]; // 경사로 설치 여부 확인

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i] - line[i + 1];

            if (diff == 0) continue; // 높이가 같으면 넘어감

            // 높이 차이가 1보다 클 경우
            if (Math.abs(diff) > 1) return false;

            // 높이가 감소하는 경우 (내리막길)
            if (diff == 1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || line[j] != line[i + 1] || visited[j]) return false;
                    visited[j] = true;
                }
            }
            // 높이가 증가하는 경우 (오르막길)
            else {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || line[j] != line[i] || visited[j]) return false;
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}