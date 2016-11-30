package reinforcementlearning;

import java.util.Random;

/**
 *
 * @author Lizzie Herman
 */
public class ReinforcementLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RaceTrack track = new RaceTrack("L-track.txt");
        // RaceTrack track = new RaceTrack("O-track.txt");
        // RaceTrack track = new RaceTrack("R-track.txt");
        int[] size = track.getSize();
        // place randomly on track
        Random random = new Random();
        int x = 0,y = 0;
        boolean safe = false;
        while(! safe){
            x = random.nextInt(size[1]);
            y = random.nextInt(size[0]);
            safe = track.cellSafe(x, y);
        }
        RaceCar car = new RaceCar(x,y);
        Algorithm alg = new Algorithm(track,car);
        // Algorithm alg = new ValueIteration(track,car);
        // i don't know which algorithm were are going to implement
        alg.runCar();
    }

}
