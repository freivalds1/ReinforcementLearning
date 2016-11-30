package reinforcementlearning;

import java.util.Random;

/**
 *
 * @author Lizzie Herman
 */
public class Algorithm {
    RaceTrack track;
    RaceCar car;
    double time;
    int cost;
    
    public Algorithm(RaceTrack t, RaceCar c){
        track = t;
        car = c;
        time = 0;
        cost = 0;
    }
    
    public int runCar(){
        boolean finished = false;
        int curxp, curyp;
        double curxv, curyv;
        while(! finished){
            double[] accl = findNextMove();
            cost++;
            curxp = car.getXPos();
            curyp = car.getYPos();
            time += 1;
            car.accelerate(accl[0], accl[1], 1);
            if(track.cellSafe(car.getXPos(), car.getYPos())){
                if(track.getCell(car.getXPos(), car.getYPos()) == 'F'){
                    cost--;
                    finished = true;
                }
            } else {
                // TO-DO what happens when you hit a wall
                // currently code just reverts car to previous position
                car.setPos(curxp, curyp);
                if(! track.cellSafe(curxp, curyp)){
                    System.out.println("Program messed up");
                    return 0;
                }
            }
        }
        return cost;
    }
    
    // this method returns a double array of accel it wants to do
    // this is the method we need to change
    public double[] findNextMove(){
        Random random = new Random();
        double x = (random.nextInt(1)-random.nextDouble());
        double y = (random.nextInt(1)-random.nextDouble());
        double[] accl = {x,y};
        return accl;
    }

}
