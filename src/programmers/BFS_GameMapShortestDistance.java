package programmers;

import java.util.*;

//게임맵최단거리(1844) : BFS 너비 우선 탐색
public class BFS_GameMapShortestDistance {
    public static void main(String[] args) {
        HashMap_BestAlbum c = new HashMap_BestAlbum();
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}; //=> 11

        BFS_GameMapShortestDistance T = new BFS_GameMapShortestDistance();
        System.out.println(T.solution(maps));
    }

    public int solution(int[][] maps) {
        int answer = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int x = currentPosition[0];
            int y = currentPosition[1];
            int distance = currentPosition[2];

            if (x == maps.length - 1 && y == maps[0].length - 1) {
                answer = distance;
                break;
            }

            for (int[] dir : directions)
            {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX>=0 && newY>=0
                        && newX<maps.length && newY<maps[0].length
                        && maps[newX][newY] == 1)
                {
                    queue.offer(new int[]{newX, newY, distance + 1});
                    maps[newX][newY] = 0;
                }
            }
        }

        if (answer == 0)
            return -1;

        return answer;
    }
}
