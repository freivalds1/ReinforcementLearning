package reinforcementlearning;

import java.util.Random;

/**
 *
 * @author Lizzie Herman
 */
public class Algorithm {
    protected RaceTrack track;
    protected RaceCar car;
    private double time;
    private int cost;
    private TrackGUI gui;
    private int[] orgPos;
    protected Random random;
    
    public Algorithm(RaceTrack t, RaceCar c, TrackGUI g){
        track = t;
        car = c;
        time = 0;
        cost = 0;
        gui = g;
        orgPos = new int[2];
        orgPos[0] = car.getXPos();
        orgPos[1] = car.getYPos();
        random = new Random();
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
            if(track.crash(car.getXPos(), car.getYPos(), lastxp, lastyp)){
                if(track.getCell(car.getXPos(), car.getYPos()) == 'F'){
                    cost--;
                    finished = true;
                }
            } else {
                // car.setPos(lastxp, lastyp); car.setVel(0, 0);
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
        int x2 = car.getXPos();
        int y2 = car.getYPos();
        int lastx = orgPos[0];
        int lasty = orgPos[1];
        int changex = Math.abs(x1-x2);
        int changey = Math.abs(y1-y2);
        int x,y;
        if(changex == 0){
            lastx = x1;
            if(y1<y2){
                for(; y1 < y2; y1++){
                    if(track.cellSafe(lastx, y1)) lasty = y1;
                    else break;
                }
            } else {
                for(; y1 > y2; y1--){
                    if(track.cellSafe(lastx, y1)) lasty = y1;
                    else break;
                    
                }
            }
            //System.out.println("horizontal: " + x2 + " " + y2);
        } else if(changey == 0){
            lasty = y1;
            if(x1<x2){
                for(; x1 < x2; x1++){
                    if(track.cellSafe(x1, lasty)) lastx = x1;
                    else break;
                }
            } else {
                for(; x1 > x2; x1--){
                    if(track.cellSafe(x1, lasty)) lastx = x1;
                    else break;
                    
                }
            }
            //System.out.println("vertical: " + x2 + " " + y2);
        } else {
            double slope = changey / changex;
            double yd;
            y = y1;
            yd = y1;
            x = x1;
            if(x1<x2){
                for(; x < x2; x++){
                    if(y1<y2) yd += slope;
                    else yd -= slope;
                    y = (int) yd;
                    if(!((y-1) < y1 && (y-1) < y2) && (y-1) == (int)(yd-slope)){
                        if(track.cellSafe(x, y-1)){
                            lastx = x;
                            lasty = y-1;
                        } else break;
                    }
                    if(track.cellSafe(x, y)){
                        lastx = x;
                        lasty = y;
                    } else break;
                    if(!((y+1) > y1 && (y+1) > y2) && (y+1) == (int)(yd+slope)){
                        if(track.cellSafe(x, y+1)){
                            lastx = x;
                            lasty = y+1;
                        } else break;
                    }
                }
            } else {
                for(; x > x2; x--){
                    if(y1<y2) yd += slope;
                    else yd -= slope;
                    y = (int) yd;
                    if(!((y-1) < y1 && (y-1) < y2) && (y-1) == (int)(yd-slope)){
                        if(track.cellSafe(x, y-1)){
                            lastx = x;
                            lasty = y-1;
                        } else break;
                    }
                    if(track.cellSafe(x, y)){
                        lastx = x;
                        lasty = y;
                    } else break;
                    if(!((y+1) > y1 && (y+1) > y2) && (y+1) == (int)(yd+slope)){
                        if(track.cellSafe(x, y+1)){
                            lastx = x;
                            lasty = y+1;
                        } else break;
                    }
                }
            }
            //System.out.println("slope: " + x2 + " " + y2);
        }
        car.setPos(lastx, lasty);
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
        double x = (random.nextInt(2)-random.nextDouble());
        double y = (random.nextInt(2)-random.nextDouble());
        double[] accl = {x,y};
        return accl;
    }

}
