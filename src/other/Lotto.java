package other;

import java.util.*;

//로또 번호 생성
public class Lotto {
    public static void main(String[] args) {
        Random random = new Random();

        int[] lotto = {0,0,0,0,0,0};

        Lotto lt = new Lotto();

        lotto[0] = random.nextInt(46);
        lotto[1]= lt.randomNum(lotto);
        lotto[2]= lt.randomNum(lotto);
        lotto[3]= lt.randomNum(lotto);
        lotto[4]= lt.randomNum(lotto);
        lotto[5]= lt.randomNum(lotto);

        for(int i=0; i<lotto.length; i++){
            System.out.print(lotto[i] + " ");
        }
    }

    private int randomNum(int[] num){
        Random random = new Random();
        int returnNum = 0;
        while (returnNum == num[0] || returnNum == num[1] || returnNum == num[2]
                || returnNum == num[3] || returnNum == num[4] || returnNum == num[5] ||returnNum == 0)
        {
            returnNum = random.nextInt(46);
        }
        return returnNum;
    }
}

