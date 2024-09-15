import java.util.*;
import java.io.*;

public class Main{
    static int N, M, A[][], B[][], ans;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        B = new int[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();

            for(int j=0; j<M; j++){
                A[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0; i<N; i++){
            String str = br.readLine();

            for(int j=0; j<M; j++){
                B[i][j] = str.charAt(j) - '0';
            }
        }

        solve();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(A[i][j]!=B[i][j]){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(ans);
    }

    static void solve(){
        for(int i=0; i<=N-3; i++){
            for(int j=0; j<=M-3; j++){
                if(A[i][j]!=B[i][j]){
                    changeMatrix(i, j);
                    ans++;
                }
            }
        }
    }

    static void changeMatrix(int y, int x){
        for(int i=y; i<y+3; i++){
            for(int j=x; j<x+3; j++){
                A[i][j] = A[i][j] == 1? 0 : 1;
            }
        }
    }
}