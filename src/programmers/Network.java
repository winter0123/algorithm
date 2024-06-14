package programmers;

import java.util.ArrayList;
import java.util.List;

public class Network {
    public static void main(String[] args) {
        Network T = new Network();
        int n = 4;
        int[][] computers = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 1, 1}};
        T.solution(n, computers);
    }

    public int solution(int n, int[][] computers) {
        int answer = 1;

        int[] check = new int[n];

        for(int i=0; i<n; i++){
            check[i]=0;
        }

        for(int i=0; i<n; i++){
            for(int z=0; z<n; z++){
                check[i] += computers[i][z];
            }
        }

        int num = 0;
        boolean same = true;
        for(int i=0; i<n; i++){
            if(check[i] == 1)
                answer ++;
            if(i==0){
                num = check[i];
            }else{
                if(check[i]!=num)
                    same = false;
            }
        }

        if(same && num != n)
            answer += (n/num-answer);

        if(answer > n){
            answer = n;
        }

        return answer;
    }

}
