package uni.gaborgalazzo.algo2;

public class Rebooking {

    private int availableSeats;

    public Rebooking(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int maxSaving(int[]groupSizes, int[]groupCosts){
        if(groupSizes.length != groupCosts.length)
            return -1;
        if(availableSeats <0 )
            return -1;

        int[][] ms = new int[groupSizes.length+1][availableSeats+1];
        for(int i = 1; i<groupSizes.length+1; i++){
            for(int j = 1; j<availableSeats; j++){
                if(groupSizes[i-1] <= j)
                    ms[i][j] = Math.max(ms[i-1][j-groupSizes[i-1]]+groupCosts[i-1], ms[i-1][j]);
                else
                    ms[i][j] = ms[i-1][j];

            }
        }

        return ms[groupSizes.length][availableSeats-1<0?0:availableSeats-1];

    }
}
