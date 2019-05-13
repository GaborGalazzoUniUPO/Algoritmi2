package uni.gaborgalazzo.algo2;

import java.util.ArrayList;

public class MSI {

    private final int[] s;
    private final int[] w;

    private int maxVal;

    public MSI(int[] w){

        this.w = w;

        this.s = new int[w.length+1];
        s[0] = 0;
        s[1] = w[0];
        for(int i = 2; i<=w.length; i++){
            s[i] = Math.max(s[i-1],s[i-2]+w[i-1]);
        }
        maxVal = s[w.length];

    }

    public int getMaxVal() {
        return maxVal;
    }

    public ArrayList<Integer> getOptSol(){
        ArrayList<Integer> sol = new ArrayList<>();
        int i = s.length;
        while (i>1){
            if(s[i-1] > s[i-2]+w[i-2]){
                i--;
            }else{
                sol.add(w[i-2]);
                i-=2;
            }
        }
        return sol;
    }

}
