package reinforcementlearning;

import java.util.Random;
/**
 *
 * @author Lizzie Herman
 */
public class SARSA extends Algorithm{
    private double prob;

    public SARSA(RaceTrack t, RaceCar c, TrackGUI g, double p) {
        super(t, c, g);
        prob = p;
    }
    
    public int[] findNextMove(){
        double explore = random.nextDouble();
        if(explore < prob){
            return super.findNextMove();
        }
        int[] accl = {0,0};
        
        // TO-DO implement algorithm
        
        return accl;
    }

    public String toString(){
        return "SARSA " + super.toString();
    }
}
