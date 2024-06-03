package programmers;

import java.util.*;

//순위검색(72412) : 이분검색 Binary Search
public class BS_RankSearch {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        BS_RankSearch T = new BS_RankSearch();
        int[] answer = T.solution(info,query);
        for(int i=0; i<answer.length; i++){
            System.out.println(answer[i]);
        }
    }

    HashMap<String, List<Integer>> hashMap = new HashMap<>();

    private int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String data : info) {
            String[] person = data.split(" ");
            makehashmap(person[0]+person[1]+person[2]+person[3],Integer.parseInt(person[4]));
            makehashmap("-"+person[1]+person[2]+person[3],Integer.parseInt(person[4]));
            makehashmap(person[0]+"-"+person[2]+person[3],Integer.parseInt(person[4]));
            makehashmap(person[0]+person[1]+"-"+person[3],Integer.parseInt(person[4]));
            makehashmap(person[0]+person[1]+person[2]+"-",Integer.parseInt(person[4]));

            makehashmap("--"+person[2]+person[3],Integer.parseInt(person[4]));
            makehashmap(person[0]+"--"+person[3],Integer.parseInt(person[4]));
            makehashmap(person[0]+person[1]+"--",Integer.parseInt(person[4]));
            makehashmap("-"+person[1]+"-"+person[3],Integer.parseInt(person[4]));
            makehashmap("-"+person[1]+person[2]+"-",Integer.parseInt(person[4]));
            makehashmap(person[0]+"-"+person[2]+"-",Integer.parseInt(person[4]));

            makehashmap(person[0]+"---",Integer.parseInt(person[4]));
            makehashmap("-"+person[1]+"--",Integer.parseInt(person[4]));
            makehashmap("--"+person[2]+"-",Integer.parseInt(person[4]));
            makehashmap("---"+person[3],Integer.parseInt(person[4]));

            makehashmap("----",Integer.parseInt(person[4]));
        }

        for (Map.Entry<String, List<Integer>> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            hashMap.put(key,list);
        }


        int k = 0;
        for (String data : query) {
            String[] tokens = data.split(" and ");
            String[] lastToken = tokens[3].split(" ");

            int num = Integer.parseInt(lastToken[1]);
            String key = tokens[0]+tokens[1]+tokens[2]+lastToken[0];

            List list = hashMap.get(key);

            if(list == null){
                answer[k] = 0;
                k++;
                continue;
            }
            answer[k] = serch(list.size(), num, list);

            k++;
        }
        return answer;
    }

    public void makehashmap(String key, int num){
        if(hashMap.containsKey(key)){
            List<Integer> list_tmp = hashMap.get(key);
            list_tmp.add(num);
            hashMap.put(key, list_tmp);
        }else {
            List<Integer> list = new ArrayList<Integer>();
            list.add(num);
            hashMap.put(key, list);
        }
    }

    public int serch (int n, int m, List list){
        int answer=0;
        int lt=0, rt=n-1;

        while(lt<=rt){
            int mid=(lt+rt)/2;

            if(Integer.parseInt(list.get(mid).toString())<m){
                lt = mid+1;
            }else{
                rt=mid-1;
            }
        }
        return list.size() - lt;
    }
}
