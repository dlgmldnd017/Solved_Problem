import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N, d, k, c, ans;
    static int map[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[N+k-1];

        for(int i=0; i<N; i++){
            map[i] = Integer.parseInt(br.readLine());
        }

        for(int i=N; i<N+k-1; i++){
            map[i] = map[i-N];
        }

        ans = Integer.MIN_VALUE;
        solve();

        System.out.println(ans);
    }

    static void solve(){
        int start = 0, end = 0, cnt = 1;
        int visited[] = new int[d+1];
        visited[c] = 1;

        while(end != map.length){

            visited[map[end]]++;

            if(visited[map[end]] == 1) cnt++;

            if(end >= start+k){
                visited[map[start]]--;

                if(visited[map[start]]==0) cnt--;

                start++;
            }

            ans = Math.max(ans, cnt);
            end++;
        }
    }
}