package uni.gaborgalazzo.algo2;

import uni.gaborgalazzo.algo2.my.Utils;

public class Knapsack {

    private int[] values;
    private int[] volumes;
    private int capacity;

    public Knapsack(int[] values, int[] volumes, int capacity) {
        this.values = values;
        this.volumes = volumes;
        this.capacity = capacity;
    }


    public int getMaxVal(){


        int[][] solMatrix = new int[values.length+1][capacity];

        for(int j = 1; j<values.length+1; j++) {
            for(int i = 1; i<capacity; i++){
                if (volumes[j-1] <= i)
                    solMatrix[j][i] = Math.max(solMatrix[j-1][i-volumes[j-1]]+values[j-1],solMatrix[j-1][i]);
                else
                    solMatrix[j][i] = solMatrix[j-1][i];
            }
        }

        return solMatrix[values.length][capacity-1];
    }
}
