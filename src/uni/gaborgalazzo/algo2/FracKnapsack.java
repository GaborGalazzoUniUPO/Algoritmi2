package uni.gaborgalazzo.algo2;

import it.uniupo.algoTools.MaxHeap;

public class FracKnapsack {


    private double capacity;
    private double[] volumes;
    private double[] values;

    private double[] doses;
    private double maxVal;


    public FracKnapsack(double capacity, double[] volumes, double[] values) {
        this.capacity = capacity;
        this.volumes = volumes;
        this.values = values;

        this.maxVal = 0;

        this.doses = new double[volumes.length];

        MaxHeap<Integer, Double> maxHeap = new MaxHeap<>();
        for(int i = 0; i<volumes.length; i++){
            maxHeap.add(i,values[i]/volumes[i]);
        }

        while (capacity>0 && !maxHeap.isEmpty()){
            int i = maxHeap.extractMax();
            if(volumes[i]<=capacity){
                capacity-=volumes[i];
                doses[i] = volumes[i];
                maxVal+=values[i];
            }else{
                double dose = capacity;
                capacity-=dose;
                doses[i] = dose;
                maxVal += values[i]*dose/volumes[i];
            }

        }

    }

    public double maxVal(){
        return maxVal;
    }

    public double dose(int i){
        return doses[i];
    }
}
