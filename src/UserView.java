import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class UserView extends JFrame {
    private User user;

    private JFrame jFrame = this;

    private JPanel contentPane;

    private JTextField txtUserId;


    private JTextField txtEnterTweetMessage;


    private MainFrame mainFrame;

    private List<Post> newsfeed;

    private JTextArea textArea;

    private JTextArea textArea_1;


    /**
     * Create user view frame.
     */
    public UserView(User user, boolean b) {

        // setting of the user view frame
        this.user = user;
        setTitle(user.ID);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 454, 412);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // The text filed entering user ID
        txtUserId = new JTextField();
        txtUserId.setHorizontalAlignment(SwingConstants.CENTER);
        txtUserId.setText("Enter User ID here");
        txtUserId.setBounds(25, 11, 189, 44);
        contentPane.add(txtUserId);
        txtUserId.setColumns(10);

        // Button for follow user
        JButton btnNewButton = new JButton("Follow User");
        btnNewButton.setBackground(Color.lightGray);
        btnNewButton.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton.setFocusable(false);
        btnNewButton.addActionListener(e -> followUser());
        btnNewButton.setBounds(224, 11, 189, 44);
        contentPane.add(btnNewButton);

        // Text field for post
        txtEnterTweetMessage = new JTextField();
        txtEnterTweetMessage.setHorizontalAlignment(SwingConstants.CENTER);
        txtEnterTweetMessage.setText("Enter Tweet Message");
        txtEnterTweetMessage.setBounds(25, 177, 189, 44);
        contentPane.add(txtEnterTweetMessage);
        txtEnterTweetMessage.setColumns(10);

        // Button for post
        JButton btnNewButton_1 = new JButton("Post Tweet");
        btnNewButton_1.setBackground(Color.lightGray);
        btnNewButton_1.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_1.setFocusable(false);
        btnNewButton_1.addActionListener(e -> postMessage());
        btnNewButton_1.setBounds(224, 177, 189, 44);
        contentPane.add(btnNewButton_1);

        // Panel holds label(List View(current following), and text area.
        JPanel panel = new JPanel();
        panel.setBounds(25, 64, 388, 102);
        contentPane.add(panel);
        panel.setLayout(null);

        // label holds message
        JLabel lblNewLabel = new JLabel("List View (Current Following)");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setBounds(0, 0, 388, 25);
        panel.add(lblNewLabel);

        // text area for post
        textArea = new JTextArea();
        textArea.setBounds(0, 24, 388, 78);
        panel.add(textArea);

        //Panel holds label(List view(new feed)), and text area which holds post
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(25, 232, 388, 130);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // label holds List view(new feed)
        JLabel lblNewLabel_1 = new JLabel("List View (News Feed)");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel_1.setBounds(0, 0, 388, 29);
        panel_1.add(lblNewLabel_1);

        // Text area to show new feeds
        textArea_1 = new JTextArea();
        textArea_1.setBounds(0, 32, 388, 98);
        panel_1.add(textArea_1);

        updateFollowing();

    }


    /**
     * check user if existed, if existed then follow the user, otherwise tells user not existed
     */
    void followUser() {
        String id = txtUserId.getText();
        int index = mainFrame.getIndexByUserId(id);
        if (index == -1 || id.equals(user.ID)) {
            JOptionPane.showMessageDialog(jFrame, "User not existed");
        } else {
            User searchUser = mainFrame.userList.get(index);
            this.user.follow(searchUser);
            txtUserId.setText("");
            updateFollowing();
            updatePost(true);
        }
    }

    /**
     * update the latest following list
     */
    void updateFollowing() {
        String ss = "";
        for (User s : user.followingsList) {
            ss += String.format(" - %s\n", s.ID);
        }
        textArea.setText(ss);
    }

    /**
     * update the post
     *
     * @param active
     */
    void updatePost(boolean active) {
        newsfeed = new ArrayList<>();
        for (User s : user.followersList) {
            newsfeed.addAll(s.posts);
        }
        newsfeed.addAll(user.posts);
        newsfeed.sort((a, b) -> (int) (b.time - a.time));
        updateNewsFeed();
        if (active) {
            mainFrame.updateAllFollowers(user);
        }
    }

    /**
     * update the text area that show post
     */
    void updateNewsFeed() {

        String postView = "";
        for (Post message : newsfeed) {

            postView += String.format(" - %s:%s\n", message.getPostID(), message.getData());
        }
        textArea_1.setText(postView);

    }

    /**
     * get the user input, and post message
     */
    void postMessage() {

        String data = this.txtEnterTweetMessage.getText();

        if (data.length() > 0) {

            user.postMessage(data);

            this.txtEnterTweetMessage.setText("");
        }

        this.updatePost(true);
    }

    void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


}



