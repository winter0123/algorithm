package theory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_Tomato {
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};
    static int[][] board, visit; //board=미로, visit 방문한곳 길 숫자로 표시
    static int n,m;
    static Queue<Point> Q = new LinkedList<>();

    public void BFS(){
        while (!Q.isEmpty()) {
            Point nowQ = Q.poll(); //현재위치 꺼내기

            //현재지점 기준 상하좌우 x,y for문
            for(int i = 0; i<4; i++){
                int newX = nowQ.x+dx[i];
                int newY = nowQ.y+dy[i];

                //board 안 && 방문 가능한곳 체크
                if(newX>= 0 && newX<n && newY >= 0 && newY<m && board[newX][newY] == 0){
                    board[newX][newY] = 1; //방문할곳 board=1로 변경
                    Q.offer(new Point(newX,newY));  //방문할곳 Q에 추가
                    visit[newX][newY] = visit[nowQ.x][nowQ.y]+1; //방문할곳 위치 숫자 = 현재숫자+1
                }
            }
        }
    }
    public static void main(String[] args) {
        BFS_Tomato T = new BFS_Tomato();
        Scanner kb = new Scanner(System.in);
        m = kb.nextInt();
        n = kb.nextInt();
        board = new int[n][m];  //미로
        visit = new int[n][m];    //간곳 표시
        for (int i = 0; i <n; i++) {
            for (int j = 0; j <m ; j++) {
                board[i][j]=kb.nextInt();
                if(board[i][j]==1)  Q.offer(new Point(i,j));
            }
        }

        T.BFS(); //출발
        boolean flag = true;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i <n; i++) {
            for (int j = 0; j <m ; j++) {
                if(board[i][j]==0)  flag=false;
            }
        }
        
        if(flag){
            for (int i = 0; i <n; i++) {
                for (int j = 0; j <m ; j++) {
                    answer = Math.max(answer, visit[i][j]);
                }
            }
            System.out.println(answer);
        }else{
            System.out.println(-1);
        }
    }
}
