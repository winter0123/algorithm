package programmers;

//피로도(87946) : DFS 깊이우선탐색
public class DFS_Tiredness {
    public static void main(String[] args) {
        //input 80
        //input [[80,20],[50,40],[30,10]]
        //result 3

        int k = 80;
        int[][] dungeons = {{80,20},{50, 40},{30, 10}}; //["최소 필요 피로도", "소모 피로도"]

        DFS_Tiredness T = new DFS_Tiredness();
        System.out.println(T.solution(k,dungeons));
    }

    public int solution(int k, int[][] dungeons) {
        int answer = 0;

        int[] visit = new int[dungeons.length];
        for(int i=0; i<visit.length; i++){
            visit[i] = 0;
        }

        answer = DFS(k,dungeons, visit);

        return answer;
    }

    private int DFS(int tiredness, int[][] dungeons, int[] visit){
        if(tiredness <= 0)
            return 0;

        int maxCount = 0;

        for(int i=0; i<dungeons.length; i++){
            int needTiredness = dungeons[i][0]; //필요 피로도
            int minusTiredness = dungeons[i][1];//소모 피로도
            if(tiredness >= needTiredness && visit[i] == 0) {
                visit[i] = 1; //방문한곳 표시 1

                int nowCount = 1+ DFS(tiredness - minusTiredness, dungeons, visit); // 현재 던전을 탐험한 후 다음 던전으로 이동

                maxCount = Math.max(maxCount,nowCount); //nowCount와 기존 maxCount 비교

                visit[i] = 0; //빠져나온곳 표시 0
            }
        }
        return maxCount;
    }

}
