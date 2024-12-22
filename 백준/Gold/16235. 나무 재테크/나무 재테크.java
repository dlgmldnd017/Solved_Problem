import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] arr, nutrient; // arr: 땅의 양분, nutrient: 겨울에 추가될 양분
    static LinkedList<Integer>[][] trees; // 나무 나이 저장

    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        nutrient = new int[N][N];
        trees = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5; // 초기 양분은 5
                trees[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[y][x].add(age); // 나무 심기
        }

        for (int year = 0; year < K; year++) {
            springAndSummer();
            fall();
            winter();
        }

        // 살아남은 나무 개수 세기
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += trees[i][j].size();
            }
        }

        System.out.println(ans);
    }

    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j].isEmpty()) continue;

                LinkedList<Integer> nextTrees = new LinkedList<>();
                int deadNutrients = 0;

                // 봄: 나무 성장 & 죽은 나무 처리
                for (int age : trees[i][j]) {
                    if (arr[i][j] >= age) { // 성장 가능
                        arr[i][j] -= age;
                        nextTrees.add(age + 1); // 나이 1 증가
                    } else { // 죽은 나무 → 양분으로 변환
                        deadNutrients += age / 2;
                    }
                }

                arr[i][j] += deadNutrients; // 여름: 죽은 나무의 양분 추가
                trees[i][j] = nextTrees; // 나무 갱신
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 == 0) { // 번식 가능한 나무
                        for (int d = 0; d < 8; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];

                            if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                                trees[ny][nx].addFirst(1); // 어린 나무는 리스트 앞에 추가
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] += nutrient[i][j]; // 겨울에 양분 추가
            }
        }
    }
}