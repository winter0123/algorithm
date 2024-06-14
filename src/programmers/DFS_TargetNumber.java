package programmers;

//타겟넘버(43165) : DFS 깊이우선탐색
public class DFS_TargetNumber {
    int count = 0;
    int number[] = null;
    int target = 0;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int targets = 3;

        DFS_TargetNumber T = new DFS_TargetNumber();
        System.out.println( T.solution(numbers, targets));
    }

    public int solution(int[] numbers, int targets) {
        int answer = 0;

        number = numbers;
        target = targets;

        DFS(0 ,0);
        answer = count;

        return answer;
    }

    private void DFS(int depth, int nextNum){
        if (depth == number.length && nextNum == target)
            count ++;

        if (depth == number.length)
            return;

        int plus  = nextNum + number[depth];
        int minus = nextNum - number[depth];

        DFS(depth+1, plus);
        DFS(depth+1, minus);
    }
}
