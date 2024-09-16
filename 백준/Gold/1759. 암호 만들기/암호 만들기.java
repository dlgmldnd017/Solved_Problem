import java.util.*;
import java.io.*;

public class Main{
    static int L, C;
    static char map[], code[];
    static TreeSet<Character> set = new TreeSet<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++){
            String c = st.nextToken();
            set.add(c.charAt(0));
        }

        map = new char[C];

        for(int i=0; i<C; i++){
            map[i] = set.pollFirst();
        }

        code = new char[L];

        solve(0, 0);
    }

    static void solve(int depth, int idx){
        if(depth == L){
            if(isValid()) System.out.println(code);
            return;
        }

        for(int i=idx; i<C; i++){
            code[depth] = map[i];
            solve(depth+1, i+1);
        }
    }

    static boolean isValid(){
        int mo=0, ja=0;

        for(int i=0; i<L; i++){
            if(code[i]=='a' || code[i]=='e' || code[i]=='i' || code[i]=='o' || code[i]=='u') mo++;
            else ja++;
        }

        if(mo>=1 && ja>=2) return true;

        return false;
    }
}