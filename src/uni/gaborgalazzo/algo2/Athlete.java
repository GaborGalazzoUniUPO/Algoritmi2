package uni.gaborgalazzo.algo2;

public class Athlete {

    private int disNum;


    public Athlete(int disNum) {
        this.disNum = disNum;
    }

    public int choice(int[] a1Yield, int[] a2Yield ){

        int[] sa1 = new int[disNum+1];
        int[] sa2 = new int[disNum+1];

        sa1[0] = 0;
        sa2[0] = 0;
        sa1[1] = a1Yield[0];
        sa2[1] = a2Yield[0];

        for (int i = 2; i<=disNum; i++){
            sa1[i] = Math.max(sa1[i-1], sa1[i-2]+a1Yield[i-1]);
        }

        for (int i = 2; i<=disNum; i++){
            sa2[i] = Math.max(sa2[i-1], sa2[i-2]+a2Yield[i-1]);
        }
        if(sa1[disNum] > sa2[disNum])
            return 1;
        else if (sa1[a1Yield.length] == sa2[a1Yield.length]){
            return 0;
        }else {
            return 2;
        }
    }
}
