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
            for(int i = length-1; i >= 0; i--){
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
    
    public boolean cellSafe(int x, int y){
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

    public boolean crash(int x1, int y1, int x2, int y2){
        char cell;
        if(! cellSafe(x1,y1)){
            return false;
        }
        try {
            if(y1 == y2 && x1 == x2){
                return cellSafe(x1,y1);
            } else if(y1 == y2){
                if(x1 < x2){
                    for(int x = x1; x <= x2; x++){
                        cell = track[y1][x];
                        if(cell == '#') return false;
                    }
                } else {
                    for(int x = x2; x <= x1; x++){
                        cell = track[y1][x];
                        if(cell == '#') return false;
                    }
                }
            } else if(x1 == x2){
                if(y1 < y2){
                    for(int y = y1; y <= y2; y++){
                        cell = track[y][x1];
                        if(cell == '#') return false;
                    }
                } else {
                    for(int y = y2; y <= y1; y++){
                        cell = track[y][x1];
                        if(cell == '#') return false;
                    }
                }
            } else if(Math.abs(x1-x2) == Math.abs(y1-y2)){
                int change = Math.abs(x1-x2);
                int x,y;
                if(x1<x2) x = x1;
                else x = x2;
                if(y1<y2) y = y1; 
                else y = y2;
                for(int i = 0; i <= change; i++){
                    if(i != 0){
                        cell = track[y+i-1][x+i];
                        if(cell == '#') return false;
                    }
                    cell = track[y+i][x+i];
                    if(cell == '#') return false;
                    if(i != change){
                        cell = track[y+i+1][x+i];
                        if(cell == '#') return false;
                    }
                }
            } else {
                int changex = Math.abs(x1-x2);
                int changey = Math.abs(y1-y2);
                double slope = changey / changex;
                int x,y;
                double yd;
                if(x1<x2) x = x1;
                else x = x2;
                if(y1<y2){
                    y = y1;
                    yd = y1;
                }
                else{
                    y = y2;
                    yd = y2;
                }
                for(int i = 0; i <= changex; i++){
                    yd += slope;
                    y = (int) yd;
                    if(i != 0 && y-1 == (int)(yd-slope)){
                        cell = track[y-1][x+i];
                        if(cell == '#') return false;
                    }
                    cell = track[y][x+i];
                    if(cell == '#') return false;
                    if(i != changex && y+1 == (int)(yd+slope)){
                        cell = track[y+1][x+i];
                        if(cell == '#') return false;
                    }
                }
            }
            return true;
        } catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public char getCell(int x, int y){
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
