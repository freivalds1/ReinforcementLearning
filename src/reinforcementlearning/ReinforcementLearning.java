package reinforcementlearning;

import java.util.Arrays;
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
        char[] letters = {'L','O','R'};
        char letter = 'L';
        String filename;
        switch(letter){
            case 'L':
                filename = "L-track.txt";
                break;
            case 'O':
                filename = "O-track.txt";
                break;
            case 'R':
                filename = "R-track.txt";
                break;
            default:
                filename = "";
        }
        RaceTrack track = new RaceTrack(filename);
        int[] size = track.getSize();
        // create gui to show what is happening
        TrackGUI gui = new TrackGUI(track.getCopyOfTrack());
        gui.pack();
        switch(letter){
            case 'L':
                gui.setSize(800, 499);
                break;
            case 'O':
                gui.setSize(800, 959);
                break;
            case 'R':
                gui.setSize(800, 1054);
                break;
        }
        gui.setVisible(true);
        // place randomly on track
        Random random = new Random();
        int x = 0,y = 0;
        boolean safe = false;
        while(! safe){
            x = random.nextInt(size[1]);
            y = size[0] - (1 + random.nextInt(size[0]));
            safe = track.cellSafe(x, y);
            gui.updateTrack(x, y, 0, 0, 0, 0);
        }
        RaceCar car = new RaceCar(x,y);
        Algorithm alg = new Algorithm(track,car,gui);
        // Algorithm alg = new ValueIteration(track,car,gui);
        // Algorithm alg = new SARSA(track,car,gui);
        alg.runCar();
        gui.updateTrack(car.getXPos(), car.getYPos(), car.getXVel(), car.getYVel(), alg.getTime(), alg.getCost());
        switch(letter){
            case 'L':
                gui.setSize(800, 500);
                break;
            case 'O':
                gui.setSize(800, 960);
                break;
            case 'R':
                gui.setSize(800, 1055);
                break;
        }
    }

}
