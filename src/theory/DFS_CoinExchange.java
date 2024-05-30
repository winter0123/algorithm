package theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DFS,BFS 동전교환
public class DFS_CoinExchange {
    //동전의 개수와 종류, 거슬러줄 금액이 주어진다. 거슬러줄 동전의 최소 갯수를 구하시오

    static int n, m, answer = Integer.MAX_VALUE;

    public void DFS(int L, int sum, int[] arr){
        if(m<sum)
            return;

        if(m==sum){
            answer = Math.min(answer,L);
        }else{
            for(int i=0; i<n; i++){
                DFS(L+1,sum+arr[i], arr);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        //입력값
        //3 : 동전의 종류 갯수
        //1 2 5 : 동전의 종류
        //15 : 금액

        //정답 3
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringTokenizer st3 = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st1.nextToken());
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        m = Integer.parseInt(st3.nextToken());

        DFS_CoinExchange T = new DFS_CoinExchange();
        T.DFS(0,0, arr);
        System.out.println(answer);
    }
}
