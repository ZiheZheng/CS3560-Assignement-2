import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Statistic extends JFrame{

    JFrame jFrame;

    JTextArea jtextArea;

    List<String> uList;

    /**
     * Use the text area to hold message
     */
    void initializeView(){

        // Create the Text Area to show message
        jtextArea = new JTextArea();
        jtextArea.setEnabled(false);
        jtextArea.setBounds(0,0,350,500);
        jFrame.add(jtextArea);

        jtextArea.setFont(new Font("Time new Roman",Font.BOLD, 18));

        for (String user : uList){
            jtextArea.append(user + "\n");
        }
    }

    /**
     * Create the statistic frame
     * @param title
     * @param userList
     */

    public Statistic(String title, List<String> userList){

        jFrame = this;
        this.uList = userList;
        this.setTitle(title);
        this.setBounds(200,200,350,500);
        initializeView();
        this.setVisible(true);
    }


}
