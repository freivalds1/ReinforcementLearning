package reinforcementlearning;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Lizzie Herman
 */
public class TrackGUI extends JFrame{
    private GridLayout boardLayout;
    private char[][] track;
    private int[] carPos = {0,0};
    private char oldChar = '#';
    
    public TrackGUI(char[][] board){
        //setSize(new Dimension(900,600));
        boardLayout = new GridLayout(board.length, board[0].length);
        track = board;
        initPanels();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Race Track");
    }
    
    public void initPanels(){
        panel.setLayout(boardLayout);
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[i].length; j++){
                JLabel label = new JLabel(Character.toString(track[i][j]));
                label.setFont(new java.awt.Font("Monospaced", 0, 24));
                panel.add(label);
            }
        }
        
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(3, 2));
        xPos.setText("X Position: ");
        xPos.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        yPos.setText("Y Position: ");
        yPos.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        xVel.setText("X Velocity: ");
        xVel.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        yVel.setText("Y Velocity: ");
        xVel.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        time.setText("Time: ");
        time.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        cost.setText("Cost: ");
        cost.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18));
        stats.add(xPos);
        stats.add(yPos);
        stats.add(xVel);
        stats.add(yVel);
        stats.add(time);
        stats.add(cost);
        
        this.add(panel, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(stats, BorderLayout.SOUTH);
    }
    
    public void updateTrack(int x, int y, double xv, double yv, double t, int c){
        int y1 = track.length - (y+1);
        track[carPos[1]][carPos[0]] = oldChar;
        oldChar = track[y1][x];
        track[y1][x] = 'O';
        carPos[0] = x;
        carPos[1] = y1;
        
        xPos.setText("X Position: " + x);
        yPos.setText("Y Position: " + y);
        xVel.setText("X Velocity: " + xv);
        yVel.setText("Y Velocity: " + yv);
        time.setText("Time: " + t);
        cost.setText("Cost: " + c);
        
        JPanel newPanel = new JPanel();
        newPanel.setLayout(boardLayout);
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[i].length; j++){
                JLabel label = new JLabel(Character.toString(track[i][j]));
                label.setFont(new java.awt.Font("Monospaced", 0, 24));
                newPanel.add(label);
            }
        }
        this.remove(panel);
        panel = newPanel;
        this.add(panel, BorderLayout.NORTH);
    }
    
    public char getLastChar(){
        return oldChar;
    }
    
    private JPanel panel = new JPanel();
    private JLabel xPos = new JLabel();
    private JLabel yPos = new JLabel();
    private JLabel xVel = new JLabel();
    private JLabel yVel = new JLabel();
    private JLabel time = new JLabel();
    private JLabel cost = new JLabel();
}
