import java.util.*;
import java.io.*;

class Meeting implements Comparable<Meeting>{
    int S, E;

    public Meeting(int S, int E){
        this.S = S;
        this.E = E;
    }

    public int compareTo(Meeting m){
        if(this.E==m.E) return this.S-m.S;
        return this.E-m.E;
    }
}

public class Main{
    static int N, ans;
    static List<Meeting> list = new ArrayList<>();

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            list.add(new Meeting(S, E));
        }

        Collections.sort(list);

        solve();

        System.out.println(ans);
    }

    static void solve(){
        int curTime = 0;

        for(Meeting m : list){
            if(m.S<curTime) continue;

            curTime = m.E;
            ans++;
        }
    }
}