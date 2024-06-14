package programmers;

import java.util.*;

//베스트엘범(42579) : HashMap
public class HashMap_BestAlbum {
    public static void main(String[] args) {
        //속한 노래가 많이 재생된 장르를 먼저 수록합니다.
        HashMap_BestAlbum c = new HashMap_BestAlbum();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays ={500, 600, 150, 800, 2500};
        //=> [4, 1, 3, 0]
        System.out.println(Arrays.toString(c.solution(genres, plays)));
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<String, Integer>(); //(장르,누적play합계)
        HashMap<String, List> mapList = new HashMap<String, List>();   //(장르,plays list)
        HashMap<String, Integer> mapNumber = new HashMap<String, Integer>(); //(장르+play수+"1",기존idx위치)

        List<Integer> answerLists = new ArrayList<Integer>(); //정답 출력 갯수를 알기위한 list

        for(int i=0; i<genres.length; i++){
            List<Integer> list = new ArrayList<Integer>();

            //idx 배열 위치를 가져오기 위한 mapNumber
            if(mapNumber.containsKey(genres[i]+plays[i]+"1")){
                mapNumber.put(genres[i]+plays[i]+"2",i);
            }else if(!mapNumber.containsKey(genres[i]+plays[i]+"2")){
                mapNumber.put(genres[i]+plays[i]+"1",i);
            }

            //play 높은값으로 장르를 담기 위한 map
            //장르별 plays값을 담는 mapList
            if(map.containsKey(genres[i])){ //이미 map에 장르가 있다면
                map.put(genres[i],map.get(genres[i])+plays[i]);
                list = mapList.get(genres[i]);
            }else{ //처음 나온 장르면
                map.put(genres[i],plays[i]);
            }

            list.add(plays[i]);
            mapList.put(genres[i],list);
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1))); //play높은 순으로 정렬

        int eachNum=0;
        int answerNum = 0;

        for (String key : keySet) {
            eachNum=0;
            List<Integer> list = mapList.get(key); //해당장르 list 가져오기
            list.sort(Comparator.reverseOrder());  //해당장르 list 정렬

            for (int keyList : list) {
                if(eachNum==2) break;
                answerLists.add(mapNumber.get(key+keyList+"1"));
                answerNum++;
                eachNum++;
                if(mapNumber.containsKey(key+keyList+"2")){
                    answerLists.add(mapNumber.get(key+keyList+"2"));
                    answerNum++;
                    eachNum++;
                }
            }
        }

        //list 정답 담긴것 배열로 변환
        int[] answer = new int[answerLists.size()];
        int idx = 0;
        for (int answerList : answerLists) {
            answer[idx] = answerLists.get(idx);
            idx++;
        }

        return answer;
    }
}
