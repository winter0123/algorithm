package programmers;

import java.util.Arrays;

//여행경로 43164 : 푸는 중
public class TravelRoute {
    public static String[] result;
    public static void main(String[] args) {
        TravelRoute T = new TravelRoute();
       // String[][] tickets =  {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[][] tickets =  {{"ICN", "A"},{"A", "B"}, {"A", "C"}, {"C", "A"}, {"B", "D"}};
        //["ICN","A","C","A","B","D"]
        T.solution(tickets);
        for(int i = 0; i < tickets.length; i++){
            System.out.println(result[i]);
        }
    }

    public String[] solution(String[][] tickets) {
        result = new String[tickets.length+1];
        boolean[] visited = new boolean[tickets.length];

        tickets = Arrays.stream(tickets)
                .sorted((t1, t2) -> t1[1].compareTo(t2[1]))
                .toArray(String[][]::new);

        String endPoint = "";

        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0].equals("ICN")){
                result[0] = tickets[i][0];
                endPoint = tickets[i][1];
                visited[i] = true;
                if(DFS(1, tickets, visited, endPoint))
                {
                    return result;
                } else {
                    visited = new boolean[tickets.length];
                    result = new String[tickets.length+1];
                }
            }
        }
        return result;
    }

    public boolean DFS (int index, String[][] tickets, boolean[] visited, String endPoint){

        for(int i = 0; i < tickets.length; i++){
            if(visited[i])
                continue;

            if(endPoint.equals(tickets[i][0])){
                result[index] = tickets[i][0];
                endPoint = tickets[i][1];
                visited[i] = true;
                index++;

                if (index >= tickets.length){
                    result[index] = tickets[i][1];
                    return true;
                }

                if(DFS(index, tickets, visited, endPoint)){
                    return true;
                }
                visited[i] = false;
                result[index] = null;
            }
        }
        return false;
    }
}
