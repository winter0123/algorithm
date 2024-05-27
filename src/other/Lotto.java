package other;

import java.util.*;

//로또 번호 생성
public class Lotto {
    public static void main(String[] args) {
        int lotto[] = {0,0,0,0,0,0};
        Lotto lt = new Lotto();

        for(int i=0; i<6; i++){
            lotto[i] = lt.RendomNum(lotto);
        }
    }

    private int RendomNum(int[] Num){
        Random random = new Random();
        int outNum = 0;
        while (outNum == Num[0] || outNum == Num[1] || outNum == Num[2]
                || outNum == Num[3] || outNum == Num[4] || outNum == 0)
        {
            outNum = random.nextInt(46);
        }
        System.out.print(outNum+",");
        return outNum;
    }
}

