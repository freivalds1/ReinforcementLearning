package reinforcementlearning;

import java.util.Random;

/**
 *
 * @author Lizzie Herman
 */
public class Algorithm {
    private RaceTrack track;
    private RaceCar car;
    private double time;
    private int cost;
    private TrackGUI gui;
    
    public Algorithm(RaceTrack t, RaceCar c, TrackGUI g){
        track = t;
        car = c;
        time = 0;
        cost = 0;
        gui = g;
    }
    
    public void runCar(){
        boolean finished = false;
        int lastxp, lastyp;
        while(! finished){
            gui.updateTrack(car.getXPos(), car.getYPos(), car.getXVel(), car.getYVel(), time, cost);
            double[] accl = findNextMove();
            if(car.accelNotWork(accl[0], accl[1], 1)) continue;
            cost++;
            lastxp = car.getXPos();
            lastyp = car.getYPos();
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
                car.setPos(lastxp, lastyp);
                if(! track.cellSafe(car.getXPos(), car.getYPos())){
                    gui.updateTrack(car.getXPos(), car.getYPos(), car.getXVel(), car.getYVel(), time, cost);
                    System.out.println("Program messed up");
                    return;
                }
            }
        }
    }
    
    public int getCost(){
        return cost;
    }
    
    public double getTime(){
        return time;
    }
    
    // this method returns a double array of accel it wants to do
    // this is the method we need to change
    public double[] findNextMove(){
        Random random = new Random();
        double x = (random.nextInt(2)-random.nextDouble());
        double y = (random.nextInt(2)-random.nextDouble());
        double[] accl = {x,y};
        return accl;
    }

}
