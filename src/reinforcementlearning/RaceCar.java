package reinforcementlearning;

import java.util.Random;

/**
 *
 * @author Lizzie Herman
 */
public class RaceCar {
    int xpos;
    int ypos;
    double xvel;
    double yvel;
    
    public RaceCar(int x, int y){
        xpos = x;
        ypos = y;
        xvel = 0;
        yvel = 0;
    }
    
    public int getXPos(){
        return xpos;
    }

    public int getYPos(){
        return ypos;
    }

    public double getXVel(){
        return xvel;
    }

    public double getYVel(){
        return yvel;
    }
    
    public void setPos(int x, int y){
        xpos = x;
        ypos = y;
    }
    
    public void setVel(double x, double y){
        xvel = x;
        yvel = y;
    }
    
    // t is the amount of time that happened during that step not total elapsed time
    public int[] accelerate(double xacc, double yacc, double t){
        Random random = new Random();
        double accelerate = random.nextDouble();
        // 80% chance of actually accelerating
        if(accelerate < 0.8){
            xvel += (xacc * t);
            yvel += (yacc * t);
        }
        xpos += (xvel * t);
        ypos += (yvel * t);
        int[] pos = {xpos, ypos};
        return pos;
    }

}
