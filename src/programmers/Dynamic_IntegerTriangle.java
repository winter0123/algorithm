package programmers;

//정수삼각혁 : 43105 동적계획법
public class Dynamic_IntegerTriangle {
    public static void main(String[] args) {

        Dynamic_IntegerTriangle t = new Dynamic_IntegerTriangle();
        int[][] triangle = {{7}, {3, 8},{8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(t.solution(triangle));
    }

    public int solution(int[][] triangle) {
        int answer = 0;
        int[] sum =  new int [triangle.length];

        for(int i=triangle.length-1; i>=0; i--){
            for(int j=0; j<triangle[i].length; j++){
                if(i==triangle.length-1){
                    sum[j] = triangle[i][j];
                    continue;
                }

                if(j==triangle[i].length){
                    continue;
                }

                if(sum[j] < sum[j+1]){
                    sum[j] = sum[j+1]+triangle[i][j];
                }else{
                    sum[j] = sum[j]+triangle[i][j];
                }
            }
        }

        for(int i=0; i<sum.length; i++){
            if(answer<sum[i]){
                answer = sum[i];
            }
        }
        return answer;
    }
}
