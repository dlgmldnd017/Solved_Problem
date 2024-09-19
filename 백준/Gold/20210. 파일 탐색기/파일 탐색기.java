import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;
    static char[][] arr;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new char[N][];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        quickSort(0, N - 1);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result.append(arr[i][j]);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    private static void quickSort(int left, int right) {

        if(left >= right) return;

        int pivot = sort(left, right);

        quickSort(left, pivot - 1);
        quickSort(pivot + 1, right);
    }

    private static int sort(int left, int right) {
        char[] pivot = arr[left];
        int i = left, j = right;

        while(i < j) {
            while(compare(pivot, arr[j]) == -1) j--;
            while(i < j && compare(pivot, arr[i]) == 1) i++;
            swap(i, j);
        }
        swap(left, i);

        return i;
    }

    private static int compare(char[] str1, char[] str2) { 
        int length = Math.min(str1.length, str2.length); 

        for (int i = 0; i < length; i++) {
            int p1 = 0, p2 = 0; 

            if(str1[i] >= 'A' && str1[i] <= 'Z') p1 = (str1[i] - 'A') * 2 + 1;
            else if(str1[i] >= 'a' && str1[i] <= 'z') p1 = (str1[i] - 'a') * 2 + 2;

            if(str2[i] >= 'A' && str2[i] <= 'Z') p2 = (str2[i] - 'A') * 2 + 1;
            else if(str2[i] >= 'a' && str2[i] <= 'z') p2 = (str2[i] - 'a') * 2 + 2;

            if(p1 < p2) return -1;
            else if (p1 > p2) return 1;
            else {
                if(p1 == 0) { 
                    int e1 = i, e2 = i;
                    ArrayList<Integer> num1 = new ArrayList<>(), num2 = new ArrayList<>();

                    int zero1 = 0, zero2 = 0;
                    boolean flag1 = false, flag2 = false;

                    while(e1 < str1.length && str1[e1] >= '0' && str1[e1] <= '9') {
                        if(str1[e1] != '0') flag1 = true; 
                        if(!flag1) zero1++;
                        else num1.add(str1[e1] - '0');
                        e1++;
                    }
                    while(e2 < str2.length && str2[e2] >= '0' && str2[e2] <= '9') {
                        if(str2[e2] != '0') flag2 = true;
                        if(!flag2) zero2++;
                        else num2.add(str2[e2] - '0');
                        e2++;
                    }

                    if(num1.size() < num2.size()) return -1;
                    else if (num1.size() > num2.size()) return 1;
                    else { 
                        for (int j = 0; j < num1.size(); j++) {
                            if(num1.get(j) < num2.get(j)) return -1;
                            else if(num1.get(j) > num2.get(j)) return 1;
                        }

                        if(zero1 < zero2) return -1;
                        else if(zero1 > zero2) return 1;
                        else {
                            i = e1 - 1;
                        }
                    }
                }
            }
        }

        if(str1.length < str2.length) return -1;
        else return 1;
    }

    private static void swap(int i, int j) {
        char[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}