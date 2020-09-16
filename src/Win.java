import javax.swing.JFrame;
import javax.swing.JLabel;

public class Win {
    public Win() {
    }
    
    public void winFrame(String winner){
        JFrame frame = new JFrame(winner +  " Wins");
        frame.setLocationByPlatform( true );
        frame.add( new JLabel(winner + " Wins !!!!") );
        frame.pack();
        frame.setVisible( true );
    }
}