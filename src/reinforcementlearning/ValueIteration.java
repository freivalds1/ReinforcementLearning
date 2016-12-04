package reinforcementlearning;

/**
 *
 * @author Lizzie Herman
 */
public class ValueIteration extends Algorithm{

    public ValueIteration(RaceTrack t, RaceCar c, TrackGUI g) {
        super(t, c, g);
    }
    
    public double[] findNextMove(){
        double[] accl = {0,0};
        
        // TO-DO implement algorithm
        
        return accl;
    }
    
    public String toString(){
        return "Value Iteration " + super.toString();
    }
}
