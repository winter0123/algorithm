package programmers;

import java.util.Arrays;

//여행경로 43164 : DFS
public class DFS_TravelRoute {
    public static String[] result;
    public static void main(String[] args) {
        DFS_TravelRoute T = new DFS_TravelRoute();
        //String[][] tickets =  {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[][] tickets = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};
        //String[][] tickets =  {{"ICN", "A"}, {"A", "B"}, {"A", "C"}, {"C", "A"}, {"B","D"}};
        T.solution(tickets);
        for(int i = 0; i < tickets.length; i++){
            System.out.println(result[i]);
        }
    }

    public String[] solution(String[][] tickets) {
        result = new String[tickets.length+1];
        boolean[] visited = new boolean[tickets.length];

        tickets = Arrays.stream(tickets) //도착지를 먼저 정렬, 도착지가 같으면 출발지 비교하여 정렬
                .sorted((t1, t2) -> {
                    if (t1[1].equals(t2[1])) {
                        return t1[0].compareTo(t2[0]);
                    }
                    return t1[1].compareTo(t2[1]);
                })
                .toArray(String[][]::new);

        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0].equals("ICN")){
                result[0] = tickets[i][0];
                visited[i] = true;
                if(DFS(1, tickets, visited, "ICN", tickets[i][1]))
                {
                    return result;
                } else {
                    visited = new boolean[tickets.length];
                }
            }
        }
        return result;
    }

    public boolean DFS (int index, String[][] tickets, boolean[] visited, String startPoint, String endPoint){

        for(int i = 0; i < tickets.length; i++){
            if(visited[i])
                continue;

            if(endPoint.equals("") && startPoint.equals(tickets[i][0]) ){
                endPoint = tickets[i][0];
                index--;
            }

            if(endPoint.equals(tickets[i][0])){
                result[index] = tickets[i][0];
                startPoint = tickets[i][0];
                endPoint = tickets[i][1];
                visited[i] = true;
                index++;

                if (index >= tickets.length){
                    result[index] = tickets[i][1];
                    return true;
                }else{
                    if(DFS(index, tickets, visited, startPoint, endPoint)){
                        return true;
                    }else{
                        visited[i] = false;
                        endPoint = "";
                    }
                }
            }
        }
        return false;
    }
}
