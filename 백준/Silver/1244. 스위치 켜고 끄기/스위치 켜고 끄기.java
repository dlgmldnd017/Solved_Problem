import java.util.*;
import java.io.*;

public class Main{
    static int N, K, S[], stu[][];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        S = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            S[i] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());

        stu = new int[K][2];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<2; j++){
               stu[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        printS();
    }

    static void solve(){
        for(int i=0; i<K; i++){
            switch(stu[i][0]){
                case 1:
                    changeM(stu[i][1]);
                    break;

                case 2:
                    changeW(stu[i][1]);
                    break;
            }
        }
    }

    static void changeM(int idx){
        for(int j=1; j<=N; j++) {
            if((j)%idx==0) {
                S[j] = S[j]==1?0:1;
            }
        }
    }

    static void changeW(int idx){
        int i = 1;

        S[idx] = S[idx] == 1 ? 0 : 1;

        if(idx == 1 || idx == N || S[idx-i] != S[idx+i]) return;

        do{
            S[idx-i] = S[idx-i] == 1 ? 0 : 1;
            S[idx+i] = S[idx+i] == 1 ? 0 : 1;
            i++;
        } while(inRange(idx-i, idx+i) && S[idx-i] == S[idx+i]);
    }

    static boolean inRange(int left, int right){
        return (left>=1) && (right<=N);
    }

    static void printS(){
        for(int i=1; i<=N; i++){
            System.out.print(S[i] + " ");
            if(i%20==0) System.out.println();
        }
    }
}