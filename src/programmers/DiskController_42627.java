package programmers;

import java.util.*;

//디스크컨트롤러(힙Heap)
public class DiskController_42627 {

    public static void main(String[] args) {
        DiskController_42627 diskController= new DiskController_42627();
        int[][] jobs = {{0, 3}, {1, 9},{2, 6}};
        System.out.println(diskController.solution(jobs));
    }

    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 시작시간 기준
        PriorityQueue<int[]> workQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 작업시간 기준 : 현재시간에 실행가능한 작업만

        for (int[] row : jobs) {
            queue.offer(row);
        }
        int timeCount = 0;

        while (!queue.isEmpty() || !workQueue.isEmpty()) {

            // 현재 실행가능한 작업을 workQueue에 담음
            while (!queue.isEmpty() && queue.peek()[0] <= timeCount) {
                workQueue.offer(queue.poll());
            }

            // 작업시간이 가장 짧은 작업을 workQueue에서 꺼냄
            if (!workQueue.isEmpty()) {
                int[] workRow = workQueue.poll();
                int start = workRow[0];
                int eachTime = workRow[1];

                timeCount += eachTime;          //작업시간을 timeCount에 더함
                answer += (timeCount - start);  //작업 시작전 까지으 대기시간을 더함
            } else { //workQueue안에 현재 실행할수 있는 작업이 없는경우
                timeCount = queue.peek()[0]; //시간을 다음 작업시간으로 변경함
            }
        }

        answer /= jobs.length;

        return answer;
    }
}
