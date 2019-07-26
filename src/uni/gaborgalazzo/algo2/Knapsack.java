package uni.gaborgalazzo.algo2;

import uni.gaborgalazzo.algo2.my.Utils;

import java.util.ArrayList;

public class Knapsack {

    private final int[][] solMatrix;
    private int[] values;
    private int[] volumes;
    private int capacity;

    public Knapsack(int[] values, int[] volumes, int capacity) {
        this.values = values;
        this.volumes = volumes;
        this.capacity = capacity;

        this.solMatrix = new int[values.length+1][capacity];

        for(int j = 1; j<values.length+1; j++) {
            for(int i = 1; i<capacity; i++){
                if (volumes[j-1] <= i)
                    solMatrix[j][i] = Math.max(solMatrix[j-1][i-volumes[j-1]]+values[j-1],solMatrix[j-1][i]);
                else
                    solMatrix[j][i] = solMatrix[j-1][i];
            }
        }
    }


    public int getMaxVal(){
        return solMatrix[values.length][capacity-1];
    }

    public ArrayList<Integer> getOptSol(){

        ArrayList<Integer> sol = new ArrayList<>();
        int numOgg =values.length, cap = capacity-1;

        while (numOgg>0 && cap > 0){
            if(solMatrix[numOgg][cap]>solMatrix[numOgg-1][cap])
            {
                sol.add(numOgg-1);
                numOgg--;
                cap = cap - volumes[numOgg];
            }else{
                numOgg--;
            }
        }
        return sol;

    }
}
