package theory;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
* 7*7격자판 미로 탈출 최단거리 구하기
* 출발점=(1,1) 좌측 최상단
* 격자판의 1은 벽이고 0은 도로
* 상하좌우로만 움직임
*
*/

//board 격자판, 출력값 12
//0 0 0 0 0 0 0
//0 1 1 1 1 1 0
//0 0 0 1 0 0 0
//1 1 0 1 0 1 1
//1 1 0 1 0 0 0
//1 0 0 0 1 0 0
//1 0 1 0 0 0 0

class Point{
    public int x,y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class BFS_ShortestDistance {
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};
    static int[][] board, visit; //board=미로, visit 방문한곳 길 숫자로 표시

    public void BFS(int x, int y){
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(x,y)); //방문할곳 Q에 담기
        board[x][y] =1;          //방문할곳 board에 1로 변경

        while (!Q.isEmpty()) {
            Point nowQ = Q.poll(); //현재위치 꺼내기

            //현재지점 기준 상하좌우 x,y for문
            for(int i = 0; i<4; i++){
                int newX = nowQ.x+dx[i];
                int newY = nowQ.y+dy[i];

                //board 안 && 방문 가능한곳 체크
                if(newX>= 1 && newX <=7 && newY >= 1 && newY <= 7 && board[newX][newY] == 0){
                    board[newX][newY] = 1; //방문할곳 board=1로 변경
                    Q.offer(new Point(newX,newY));  //방문할곳 Q에 추가
                    visit[newX][newY] = visit[nowQ.x][nowQ.y]+1; //방문할곳 위치 숫자 = 현재숫자+1
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS_ShortestDistance T = new BFS_ShortestDistance();
        Scanner kb = new Scanner(System.in);
        board = new int[8][8];  //미로
        visit = new int[8][8];    //간곳 표시
        for (int i = 1; i <=7; i++) {
            for (int j = 1; j <= 7 ; j++) {
                board[i][j]=kb.nextInt();
            }
        }

        T.BFS(1,1); //출발

        if(visit[7][7]==0) System.out.println(-1);
        else System.out.println(visit[7][7]);
    }

}