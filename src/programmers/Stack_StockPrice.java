package programmers;

import java.util.Stack;

//주식가격(42584): Stack
public class Stack_StockPrice {
    public static void main(String[] args) {
        Stack_StockPrice stack_StockPrice= new Stack_StockPrice();
        //int[] prices = {1, 2, 3, 4, 5, 6, 1, 1, 2, 3, 1, 2, 3}; // [12, 5, 4, 3, 2, 1, 6, 5, 2, 1, 2, 1, 0]
        int[] prices = {1, 2, 3, 2, 3}; //4,3,2,1,0

        int[] answer = stack_StockPrice.solution(prices);

        for(int num : answer){
            System.out.println(num);
        }
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> stackInt = new Stack<>();

        for(int i=0; i<prices.length; i++){
            answer[i] =prices.length-i-1;
            while(!stackInt.isEmpty() && prices[stackInt.peek()] > prices[i]){
                int index = stackInt.pop();
                answer[index] = i-index;
            }
            stackInt.add(i);
        }
        return answer;
    }

// 수정 전 코드
//    public int[] solution(int[] prices) {
//        int[] answer = new int[prices.length];
//        boolean[] isSet = new boolean[prices.length];
//
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(prices[0]);
//        isSet[0] = false;
//
//        int dataIn = 0;
//        for (int i = 1; i < prices.length; i++) {
//            int before = prices[i - 1];
//            int now = prices[i];
//
//            if (before <= now) {
//                queue.add(prices[i]);
//            } else {
//                for (int j = 0; j < i; j++) {
//                    if (!isSet[j]) {
//                        int check = queue.poll();
//                        if (check > now) {
//                            answer[j] = i - j;
//                            isSet[j] = true;
//                        } else {
//                            queue.add(check);
//                        }
//                    }
//                }
//                queue.add(prices[i]);
//            }
//        }
//
//        for (int i = 0; i < answer.length; i++) {
//            if (isSet[i] == false) {
//                answer[i] = answer.length - i - 1;
//            }
//        }
//        return answer;
//    }
}
