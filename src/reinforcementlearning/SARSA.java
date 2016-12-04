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
    
    public double[] findNextMove(){
        double explore = random.nextDouble();
        if(explore < prob){
            return super.findNextMove();
        }
        double[] accl = {0,0};
        
        // TO-DO implement algorithm
         //Q(s{t},a{t}) leftarrow Q(s{t},a{t})+ alpha [r{{t+1}}+ gamma Q(s{{t+1}},a{{t+1}})-Q(s{t},a{t})]
        //Q is the value for a state-action, updated by error
        //Q values represent the possible reward received in the next time step for taking action a in state s
        //Alpha  is the adjusted learning rate
        //gamma is the discounted future reward received from the next state-action observation.
        //
        //ALPHA - The learning rate determines to what extent the newly acquired information will override the old information. A factor of 0 will make the agent not learn anything, while a factor of 1 would make the agent consider only the most recent information.
        //GAMMA - The discount factor determines the importance of future rewards. A factor of 0 will make the agent "opportunistic" by only considering current rewards, while a factor approaching 1 will make it strive for a long-term high reward. If the discount factor meets or exceeds 1, the {\displaystyle Q} Q values may diverge.
        //LOOK AT THE CODE ATTACHED TO THIS FUCKING WEB PAGE IF IT STILL ISN'T MAKING SENSE: https://studywolf.wordpress.com/2013/07/01/reinforcement-learning-sarsa-vs-q-learning/
        return accl;
    }

    public String toString(){
        return "SARSA " + super.toString();
    }
}
