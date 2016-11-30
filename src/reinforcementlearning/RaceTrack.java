package reinforcementlearning;

import java.io.*;

/**
 *
 * @author Lizzie Herman
 */
public class RaceTrack {
    char track[][];
    
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
    
    public boolean cellSafe(int x, int y){
        try {
            char cell = track[x][y];
            if(cell == 'S' || cell == 'F' || cell == '.'){
                return true;
            }
            return false;
        } catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public char getCell(int x, int y){
        try {
            char cell = track[x][y];
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
        }
        return ' ';
    }
    
    public int[] getSize(){
        int[] size = {track.length, track[0].length};
        return size;
    }
}
