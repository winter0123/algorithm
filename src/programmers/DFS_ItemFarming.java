package programmers;


import java.awt.*;

//아이템 줍기(87694) :
public class DFS_ItemFarming {
    public static void main(String[] args) {

        int[][] rectangle = {
                {1, 1, 7, 4},
                {3, 2, 5, 5},
                {4, 3, 6, 9},
                {2, 6, 8, 8}
        };

        DFS_ItemFarming T = new DFS_ItemFarming();
        int answer = T.solution(rectangle,1,3,7,8);
        System.out.println(answer);
    }

    private int[][] position = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private Integer[][] grid;
    private Integer[][] visitCheck;

    int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 1;

        int maxX = 0;
        int maxY = 0;

        characterY *= 2;
        characterX *= 2;
        itemX *= 2;
        itemY *= 2;

        for (int i=0; i<rectangle.length; i++ ) {

            rectangle[i][0] *= 2;
            rectangle[i][1] *= 2;
            rectangle[i][2] *= 2;
            rectangle[i][3] *= 2;

            maxX = Math.max(maxX, rectangle[i][2]);
            maxY = Math.max(maxY, rectangle[i][3]);
        }

        grid = new Integer[maxY + 2][maxX + 2];
        visitCheck = new Integer[maxY + 2][maxX + 2];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = 0;
                visitCheck[y][x] = 0;
            }
        }

        for (int[] rect : rectangle) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];

            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if((y == y1 || y == y2 || x == x1 || x == x2) && grid[y][x]!=1){
                        grid[y][x] = 2;
                    }else{
                        grid[y][x] = 1;
                    }
                }
            }
        }

//        for (Integer[] row : grid) {
//            for (Integer value : row) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }

        int nowPointX1 = 0;
        int nowPointY1 = 0;
        int nowPointX2 = 0;
        int nowPointY2 = 0;

        visitCheck[characterY][characterX] = 1;

        for(int[] newPosition : position) { //현재 지점을 기준으로 8곳 탐색
            int nx = characterX + newPosition[0];
            int ny = characterY + newPosition[1];
            if(grid[ny][nx] == 2){
                visitCheck[ny][nx] = 1;

                if(nowPointX1 == 0 && nowPointY1 == 0){
                    nowPointX1 = nx;
                    nowPointY1 = ny;
                    visitCheck[nowPointY1][nowPointX1] = 1;
                }else{
                    nowPointX2 = nx;
                    nowPointY2 = ny;
                    visitCheck[nowPointY2][nowPointX2] = 1;
                }
            }
        }

        while(nowPointX1 !=0 && nowPointY1 != 0){
            Point point1 = DFS(nowPointX1, nowPointY1);
            nowPointX1 = point1.x;
            nowPointY1 = point1.y;

            Point point2 = DFS(nowPointX2, nowPointY2);
            nowPointX2 = point2.x;
            nowPointY2 = point2.y;
            answer++;

            if(itemX == nowPointX1 && itemY == nowPointY1)
                break;

            if(itemX == nowPointX2 && itemY == nowPointY2)
                break;
        }
        return answer/2;
    }

    Point DFS(int nowPointX, int nowPointY){
        for(int[] newPosition : position) { //현재 지점을 기준으로 8곳 탐색
            int nx = nowPointX + newPosition[0];
            int ny = nowPointY + newPosition[1];

            if(nx>=0 && ny>=0 && grid.length <ny  && grid[0].length < nx){
                continue;
            }

            if(visitCheck[ny][nx] == 1) continue;

            if(grid[ny][nx] == 2){
                visitCheck[ny][nx] = 1;
                return new Point(nx,ny);
            }
        }
        return new Point(0,0);
    }
}
