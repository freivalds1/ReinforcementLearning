package reinforcementlearning;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Lizzie Herman
 */
public class RaceTrack {
    private char track[][];
    
    public RaceTrack(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            line = br.readLine();
            String[] dimensions = line.split(",");
            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            track = new char[length][width];
            for(int i = 0; i < length; i++){
                line = br.readLine();
                track[i] = line.toCharArray();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public char[][] getCopyOfTrack(){
        char[][] copy = new char[track.length][track[0].length];
        for(int i = 0; i < track.length; i++){
            copy[i] = Arrays.copyOf(track[i], track[i].length);
        }
        return copy;
    }
    
    public boolean cellSafe(int x, int y1){
        int y = track.length - (1 + y1);
        try {
            char cell = track[y][x];
            if(cell == '#'){
                return false;
            }
            return true;
        } catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public char getCell(int x, int y1){
        int y = track.length - (1 + y1);
        try {
            char cell = track[y][x];
            switch(cell){
                case 'S':
                    System.out.println("At the starting line");
                    break;
                case 'F':
                    System.out.println("At finish line");
                    break;
                case '.':
                    System.out.println("Still on the track");
                    break;
                case '#':
                    System.out.println("Hit a wall");
                    break;
                default:
                    System.out.println("Unknown cell");
                    break;
            }
            return cell;
        } catch(IndexOutOfBoundsException e){
            System.out.println("Went outside the grid");
            return ' ';
        }
    }
    
    public int[] getSize(){
        int[] size = {track.length, track[0].length};
        return size;
    }
}
