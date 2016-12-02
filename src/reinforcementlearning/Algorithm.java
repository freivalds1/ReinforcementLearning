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
    private int[] orgPos;
    
    public Algorithm(RaceTrack t, RaceCar c, TrackGUI g){
        track = t;
        car = c;
        time = 0;
        cost = 0;
        gui = g;
        orgPos = new int[2];
        orgPos[0] = car.getXPos();
        orgPos[1] = car.getYPos();
    }
    
    public void runCar(boolean hit){
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
            if(track.cellSafe(car.getXPos(), car.getYPos(), lastxp, lastyp)){
                if(track.getCell(car.getXPos(), car.getYPos()) == 'F'){
                    cost--;
                    finished = true;
                }
            } else {
                if(hit){
                    hitWall();
                } else {
                    hitWall(lastxp, lastyp);
                }
                if(! track.cellSafe(car.getXPos(), car.getYPos())){
                    gui.updateTrack(car.getXPos(), car.getYPos(), car.getXVel(), car.getYVel(), time, cost);
                    System.out.println("Program messed up");
                    return;
                }
            }
        }
    }
    
    // when sent back to starting position
    public void hitWall(){
        car.setPos(orgPos[0], orgPos[1]);
        car.setVel(0, 0);
    }
    
    // when sent back to closest empty site of crash
    // x1, y1 are position before crash, car is set to pos of crash
    public void hitWall(int x1, int y1){
        car.setVel(0, 0);
        int x = car.getXPos();
        int y = car.getYPos();
        boolean jump = track.cellSafe(x, y);
        if(y < y1){
            for(; y <= y1; y++){
                if(x < x1){
                    for(; x <= x1; x++){
                        if(jump) jump = track.cellSafe(x, y);
                        if(!jump && track.cellSafe(x, y)){
                            car.setPos(x, y);
                            return;
                        }
                    }
                } else {
                    for(; x >= x1; x--){
                        if(jump) jump = track.cellSafe(x, y);
                        if(!jump && track.cellSafe(x, y)){
                            car.setPos(x, y);
                            return;
                        }
                    }
                }
            }
        } else {
            for(; y >= y1; y--){
                if(x < x1){
                    for(; x <= x1; x++){
                        if(jump) jump = track.cellSafe(x, y);
                        if(!jump && track.cellSafe(x, y)){
                            car.setPos(x, y);
                            return;
                        }
                    }
                } else {
                    for(; x >= x1; x--){
                        if(jump) jump = track.cellSafe(x, y);
                        if(!jump && track.cellSafe(x, y)){
                            car.setPos(x, y);
                            return;
                        }
                    }
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
