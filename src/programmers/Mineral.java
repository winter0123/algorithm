package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

//광물캐기(172927) : Greedy 탐욕법
public class Mineral {

    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}; //출력 12

        Mineral mineral = new Mineral();
        System.out.println(mineral.solution(picks, minerals)); // 중간 결과값 출력
    }

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int diamond = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        int total = (diamond+iron+stone)*5;

        if(total>minerals.length)
            total = minerals.length;

        String[] minerals2 = new String[total];

        for (int i = 0; i < total; i++) {
            minerals2[i] = minerals[i];
        }

        int num = minerals2.length/5+1;
        int[] addNum = new int[num];
        int[] addNum_copy = new int[num];

        int k = 0;
        for (int i = 0; i < minerals2.length; i++) {
            if (i % 5 == 0 && i != 0) k++;
            addNum[k] += minerals2[i].equals("diamond") ? 25 : minerals2[i].equals("iron") ? 5 : 1;
        }

        System.arraycopy(addNum, 0, addNum_copy, 0, addNum.length);
        Arrays.sort(addNum_copy); // 배열을 오름차순으로 정렬

        // 내림차순으로 정렬
        Integer[] addNumInteger = Arrays.stream(addNum).boxed().toArray(Integer[]::new);
        Arrays.sort(addNumInteger, Comparator.reverseOrder());
        int[] sortedArrReverse = Arrays.stream(addNumInteger).mapToInt(Integer::intValue).toArray();

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // 정렬된 배열의 요소들과 원래 배열의 요소들을 비교하여 정렬 순서를 찾음
        int[] sortOrder = new int[addNum.length];
        for (int i = 0; i < addNum.length; i++) {
            for (int j = 0; j < addNum_copy.length; j++) {
                if (addNum[i] == sortedArrReverse[j] && !hashMap.containsKey(j + 1)) {
                    hashMap.put(j + 1, i);
                    sortOrder[i] = j + 1; // 1부터 시작하는 순서를 저장합니다.
                    break;
                }
            }
        }

        // 각 숫자의 정렬된 순서대로 값을 저장할 배열
        String[] resultStr = new String[sortOrder.length];
        for (int i = 0; i < sortOrder.length; i++) {
            System.out.println(sortOrder[i]);
            if (sortOrder[i] <= diamond) {
                resultStr[i] = "diamond";
            } else if (sortOrder[i] <= diamond + iron) {
                resultStr[i] = "iron";
            } else {
                resultStr[i] = "stone";
            }
        }

        for (int i = 0; i < minerals.length; i++) {
            if (i >= (diamond + iron + stone) * 5)
                break;

            if (minerals[i].equals("diamond")) {
                if (resultStr[i / 5].equals("diamond")) {
                    answer += 1;
                } else if (resultStr[i / 5].equals("iron")) {
                    answer += 5;
                } else {
                    answer += 25;
                }
            } else if (minerals[i].equals("iron")) {
                if (resultStr[i / 5].equals("stone")) {
                    answer += 5;
                } else {
                    answer += 1;
                }
            } else { //"stone"
                answer += 1;
            }
        }
        return answer;
    }
}
