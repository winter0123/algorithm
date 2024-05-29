package programmers;

//입국심사(43238) : 이분탐색 Binary search
public class BS_Immigration {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7,10};

        BS_Immigration immigration= new BS_Immigration();
        System.out.println(immigration.solution(n, times));
    }


    public long solution(int n, int[] times) {
        long answer = 0;

        //1: 최대 시간 계산
        int maxTime = 0;

        for (int time : times) {
            maxTime = Math.max(maxTime, time);
        }

        long left = 0; // 가능한 최소 시간(첫 중간값을 잡을때 '최대시간/2'만 하면됨)
        long right = (long) maxTime * n;// 가능한 최대 시간

        //2: while 반복
        while (left <= right) {
            long midTime = (left + right) / 2;
            long total = 0;

            //3: 주어진 시간(midTime) 동안 각 심사대가 처리할 수 있는 인원 수 계산
            for (int time : times) {
                total += midTime / time;
            }

            //4: 이분 탐색
            if (total >= n) {
                answer = midTime;
                right = midTime - 1;
            } else {
                left = midTime + 1;
            }
        }
        return answer;
    }
}
