package programmers;

import java.util.Arrays;

//여행경로 43164 : 푸는 중
public class TravelRoute {
    public static String[] result;
    public static void main(String[] args) {
        TravelRoute T = new TravelRoute();
        String[][] tickets =  {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        T.solution(tickets);
        for(int i = 0; i < tickets.length; i++){
            System.out.println(result[i]);
        }
    }

    public boolean solution(String[][] tickets) {
        String[] answer = {};
        result = new String[tickets.length];
        Boolean[] visited = new Boolean[tickets.length];
        Arrays.sort(tickets[1]);

        String end = "";
        for(int i = 0; i < tickets.length; i++){
            visited[i] = false;

            if(tickets[i][0].equals("ICN") && result[0] == null){
                result[0] = tickets[i][0];
                visited[i] = true;
                end = tickets[i][1];
            }
        }
        return DFS(0, tickets, visited, end);
    }

    public boolean DFS (int idx, String[][] tickets, Boolean[] visited, String end){

        if(idx == tickets.length)
            return false;

        int i= 0;
        while(i < tickets.length){
            if(visited[i] == true){
                i++;
                continue;
            }
            String start = tickets[i][0];

            if(i != tickets.length-1){
                if(start.equals(end)){
                    result[idx+1] = start;
                    visited[i] = true;
                    boolean check = DFS(idx+1,tickets, visited,tickets[i][1]);
                    if(!check){
                        visited[i] = false;
                        DFS(idx+1,tickets, visited,tickets[i][1]);
                    }
                }
            }
            i++;
        }

        return false;
    }
}
