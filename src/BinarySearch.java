import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//이분검색 : 알고리즘
public class BinarySearch {

    //1. arr n개의 숫자를 정렬 한다
    //2. 시작점 lt, 종료점 rt를 찾는다.
    //3. 중간값 mid = (시작점 lt + 종료점 rt) / 2;
    //4. 중간값이 정답이 아닐 경우
    //4-1. arr[mid] > m 이면 rt=mid-1
    //4-2. arr[mid] < m 이면 lt=mid+1
    //5. 중간값이 M이 될때까지 3~5를 반복한다
    public int solution(int n, int m, int[] arr){
        int answer=0;
        Arrays.sort(arr);

        int lt=0, rt=n-1;

        while(lt<=rt){
            int mid=(lt+rt)/2;

            if(arr[mid]==m){
                answer=mid+1;
                break;
            }

            if(arr[mid]>m){
                rt = mid-1;
            }else{
                lt=mid+1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        //N개의 수가 주어질때 오름차순으로 정렬된 상태에서 M 숫자의 위치(순서)를 찾아라

        // 입력값
        // 8 32
        // 23 87 65 12 57 32 99 81
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st1.nextToken()); //arr갯수
        int m = Integer.parseInt(st1.nextToken()); //찾아야 할 수

        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] =  Integer.parseInt(st2.nextToken());
        }

        BinarySearch T = new BinarySearch();
        System.out.println(T.solution(n, m, arr));
    }
}
