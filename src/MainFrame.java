import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Main frame of the application
 */
public class MainFrame extends JFrame {

    /**
     * Panel to hold other widgets
     */
    private JPanel contentPane;

    /**
     * A text field to let user entering name
     */
    private JTextField txtEnterNameHere;
    /**
     * A Text field to let user entering group ID
     */
    private JTextField txtEnterGroupId;


    /**
     * Define a tree model
     */
    private JTree tree;

    /**
     * A list to store user
     */
    List<User> userList;

    /**
     * A set to store group ID
     */
    private Set<String> groupIdSet;

    /**
     * A list to store post
     */
    private List<Post> postList;

    /**
     * A list to store user view list
     */
    private List<UserView> userViewList;
    /**
     * A list to store positive message
     */
    private List<String> positivePostList;

    /**
     * create user view
     *
     * @param user
     * @return
     */
    public UserView createUserView(User user) {

        UserView userView = new UserView(user, false);
        userView.setMainFrame(this);
        userViewList.add(userView);

        return userView;

    }

    /**
     * Create the main frame.
     */
    public MainFrame() {

        // Define container to hold groupId, user, post, and user view list
        groupIdSet = new HashSet<>();
        userList = new ArrayList<>();
        postList = new ArrayList<>();
        userViewList = new ArrayList<>();

        // Define a list that contain positive words
        positivePostList = List.of("Good", "good", "Great", "great", "Happy", "happy", "Joyful", "joyful");

        // set main frame attribute
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 631, 544);
        setTitle("Jacob's Mini Twitter 2.0 @ CPP");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // define a panel to contain tree and a label
        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 186, 449);
        contentPane.add(panel);
        panel.setLayout(null);

        // create a label
        JLabel lblTreeView = new JLabel("Tree View");
        lblTreeView.setFont(new Font("Time new Roman", Font.BOLD, 13));
        lblTreeView.setBounds(10, 0, 76, 28);
        panel.add(lblTreeView);

        // Create tree node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode("CS356");

        // Define user
        User student1 = new User("stu1",dataTime(),dataTime());
        User student2 = new User("stu2",dataTime(),dataTime());
        User student3 = new User("stu3",dataTime(),dataTime());

        User student8 = new User("stu8",dataTime(),dataTime());
        User student9 = new User("stu9",dataTime(),dataTime());
        User student10 = new User("stu10",dataTime(),dataTime());

        User john = new User("John",dataTime(),dataTime());
        User bob = new User("Bob",dataTime(),dataTime());
        User steve = new User("Steve",dataTime(),dataTime());

        // create tree node
        DefaultMutableTreeNode node_2 = new DefaultMutableTreeNode("CS356Session01");

        // add children tree node to parents tree node
        node_2.add(new DefaultMutableTreeNode(student8));
        node_2.add(new DefaultMutableTreeNode(student9));
        node_2.add(new DefaultMutableTreeNode(student10));

        node_1.add(new DefaultMutableTreeNode(student1));
        node_1.add(new DefaultMutableTreeNode(student2));
        node_1.add(new DefaultMutableTreeNode(student3));

        root.add(new DefaultMutableTreeNode(john));
        root.add(new DefaultMutableTreeNode(bob));
        root.add(new DefaultMutableTreeNode(steve));

        User oostu = new User("oostu",dataTime(),dataTime());
        User ppstu2 = new User("ppstu2",dataTime(),dataTime());

        root.add(node_1);
        root.add(node_2);

        root.add(new DefaultMutableTreeNode(oostu));
        root.add(new DefaultMutableTreeNode(ppstu2));

        // add tree node to user list for later use
        userList.add(john);
        userList.add(bob);
        userList.add(steve);
        userList.add(oostu);
        userList.add(ppstu2);
        userList.add(student1);
        userList.add(student2);
        userList.add(student3);
        userList.add(student8);
        userList.add(student9);
        userList.add(student10);

        // initialized follow status -> john follows steve
        john.follow(steve);

        // create user view
        createUserView(john);
        createUserView(bob);
        createUserView(steve);
        createUserView(oostu);
        createUserView(ppstu2);
        createUserView(student1);
        createUserView(student2);
        createUserView(student3);
        createUserView(student8);
        createUserView(student9);
        createUserView(student10);

        // add parents tree nodes to list of groupId set
        groupIdSet.add("CS356");
        groupIdSet.add("CS356Session01");
        groupIdSet.add("Root");

        // Add children nodes to Root node

        tree = new JTree(root);
        tree.setEditable(true);

        tree.setBounds(10, 28, 166, 410);
        panel.add(tree);

        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();

                if (node == null)
                    return;

                Object object = node.getUserObject();
                if (node.isLeaf()) {
                    User user = (User) object;
                    System.out.println("selecting user: " + user.toString());
                } else {
                    System.out.println("selecting group: " + object.toString());
                }

            }
        });

        // read in a picture
        ImageIcon pointingFinger = new ImageIcon("pointingFinger.png");
        ImageIcon thumbUp = new ImageIcon("ThumbUp.jpg");

        // TextField for username
        txtEnterNameHere = new JTextField();
        txtEnterNameHere.setText("");
        txtEnterNameHere.setHorizontalAlignment(JButton.CENTER);
        txtEnterNameHere.setToolTipText("");
        txtEnterNameHere.setBounds(206, 11, 173, 37 + 30);
        contentPane.add(txtEnterNameHere);
        txtEnterNameHere.setColumns(10);


        // Button for add user
        JButton btnNewButton = new JButton("Add User");
        btnNewButton.setBackground(Color.lightGray);
        btnNewButton.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton.setIcon(pointingFinger);
        btnNewButton.setFocusable(false);
        btnNewButton.addActionListener(e -> addUser());
        btnNewButton.setBounds(406, 11, 199 + 5, 37 + 30);
        contentPane.add(btnNewButton);

        //TextField for Group ID
        txtEnterGroupId = new JTextField();
        txtEnterGroupId.setToolTipText("");
        txtEnterGroupId.setText("");
        txtEnterGroupId.setHorizontalAlignment(JButton.CENTER);
        txtEnterGroupId.setColumns(10);
        txtEnterGroupId.setBounds(206, 59 + 30, 173, 37 + 30);
        contentPane.add(txtEnterGroupId);

        // Button for add Group
        JButton btnAddGroup = new JButton("Add Group");
        btnAddGroup.setBackground(Color.lightGray);
        btnAddGroup.setBorder(BorderFactory.createEtchedBorder());
        btnAddGroup.setIcon(pointingFinger);
        btnAddGroup.setFocusable(false);
        btnAddGroup.addActionListener(e -> addGroup());
        btnAddGroup.setBounds(406, 59 + 30, 199 + 5, 37 + 30);
        contentPane.add(btnAddGroup);

        // Button for open user view
        JButton btnNewButton_2 = new JButton("Open User View");
        btnNewButton_2.setBackground(Color.lightGray);
        btnNewButton_2.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_2.setFocusable(false);
        btnNewButton_2.addActionListener(e -> launchUserView());
        btnNewButton_2.setBounds(206, 107 + 60, 399, 52);
        contentPane.add(btnNewButton_2);

        // Button for show user total
        JButton btnNewButton_1 = new JButton("Show User Total");
        btnNewButton_1.setBackground(Color.lightGray);
        btnNewButton_1.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_1.setFocusable(false);
        btnNewButton_1.addActionListener(e -> totalUser());
        btnNewButton_1.setBounds(206, 295, 173, 63);
        contentPane.add(btnNewButton_1);

        // Button for show Group total
        JButton btnNewButton_3 = new JButton("Show Group Total");
        btnNewButton_3.setBackground(Color.lightGray);
        btnNewButton_3.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_3.addActionListener(e -> totalGroup());
        btnNewButton_3.setFocusable(false);
        btnNewButton_3.setBounds(406, 295, 199, 63);
        contentPane.add(btnNewButton_3);

        // Button for show post total
        JButton btnNewButton_4 = new JButton("Show Post Total");
        btnNewButton_4.setBackground(Color.lightGray);
        btnNewButton_4.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_4.setFocusable(false);
        btnNewButton_4.addActionListener(e -> totalPost());
        btnNewButton_4.setBounds(206, 363, 173, 63);
        contentPane.add(btnNewButton_4);

        // Button for show Positive percentage
        JButton btnNewButton_5 = new JButton("Show Positive Percentage");
        btnNewButton_5.setBackground(Color.lightGray);
        btnNewButton_5.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_5.setFocusable(false);
        btnNewButton_5.addActionListener(e -> positivePost());
        btnNewButton_5.setBounds(406, 362, 199, 65);
        contentPane.add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("Verify user/group ID");
        btnNewButton_6.setBackground(Color.lightGray);
        btnNewButton_6.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_6.setFocusable(false);
        btnNewButton_6.addActionListener(e -> verify());
        btnNewButton_6.setBounds(206, 230, 173, 65);
        contentPane.add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("Last update");
        btnNewButton_7.setBackground(Color.lightGray);
        btnNewButton_7.setBorder(BorderFactory.createEtchedBorder());
        btnNewButton_7.setFocusable(false);
        btnNewButton_7.addActionListener(e -> lastUpdate());
        btnNewButton_7.setBounds(406, 230, 199, 63);
        contentPane.add(btnNewButton_7);


    }

    public void verify() {

        String userName = txtEnterNameHere.getText();
        String groupName = txtEnterGroupId.getText();
        String space = " ";

        if (userName != ""){

            if (getIndexByUserId(userName) != -1) {
                JOptionPane.showMessageDialog(null, "User name already exists");

            }

            if (userName.contains(space)) {
                JOptionPane.showMessageDialog(null, "User name can not contain space");

            }
        }

        if (groupName != ""){

            if (groupIdSet.contains(groupName)) {
                JOptionPane.showMessageDialog(null, "Group ID already exists");

            }

            if (groupName.contains(space)) {
                JOptionPane.showMessageDialog(null, "Group ID can not contain space");

            }
        }

    }

    /**
     * get user index by ID
     *
     * @param id
     * @return
     */
    int getIndexByUserId(String id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).ID.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * function for add user to the tree view
     */
    void addUser() {

        String userId = txtEnterNameHere.getText();


        if (userId.equals("")) {
            return;
        }
        if (getIndexByUserId(userId) != -1) {
            System.out.println("user id already exists");
            return;
        }
        User user = new User(userId,dataTime(),dataTime());
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null)
            return;
        if (node.isLeaf()) {
            node = (DefaultMutableTreeNode) node.getParent();

        }
        node.add(new DefaultMutableTreeNode(user));
        createUserView(user);
        for (int i = 0; i < node.getChildCount(); i++) {
            if (node.getChildAt(i).toString().equals("Empty")) {
                node.remove(i);
            }
        }
        userList.add(user);
        txtEnterNameHere.setText("");
        tree.updateUI();

    }

    /**
     * function for add group to tree view
     */
    void addGroup() {

        String groupId = txtEnterGroupId.getText();
        if (groupId.equals("")) {
            return;
        }
        if (groupIdSet.contains(groupId)) {
            System.out.println("group id already exists");
            return;
        }

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null)
            return;
        if (node.isLeaf()) {
            node = (DefaultMutableTreeNode) node.getParent();
        }
//        node.add(new DefaultMutableTreeNode(groupId));
        DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupId);
        node.add(group);
        group.add(new DefaultMutableTreeNode("..."));
        groupIdSet.add(groupId);
        tree.updateUI();

    }

    /**
     * Launch user view
     */
    void launchUserView() {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null)
            return;
        if (node.isLeaf()) {
            User user = (User) node.getUserObject();
            int index = getIndexByUserId(user.ID);
            if (index != -1) {
                UserView userView = userViewList.get(index);
                userView.setVisible(true);
            }
        }
    }


    public void lastUpdate() {


        List<String> list1 = null;
        try {
            list1 = UserView.postList();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You need to open user view and post " +
                    "something first");

        } finally {
            new Statistic("Last Update", list1);
        }


    }


    /**
     * function to show total user
     */
    void totalUser() {

        List<String> list1 = new ArrayList<>();

        for (User user : userList) {
            String id = user.ID;
            list1.add(id);
        }

        new Statistic("Total User", list1);
    }

    /**
     * function to show total group
     */
    void totalGroup() {

        List<String> list = new ArrayList<>();
        for (String s : groupIdSet) {
            list.add(s);
        }
        new Statistic("Total Group", list);
    }

    /**
     * function to show total posts
     */
    void totalPost() {

        List<String> list = new ArrayList<>();
        for (Post post : postList) {
            list.add(post.toString());
        }
        new Statistic("Show Messages Total", list);
    }

    /**
     * function to show positive post
     */
    void positivePost() {

        List<String> pList = new ArrayList<>();

        for (Post post : postList) {
            String data = post.getData();

            for (String s : positivePostList) {
                if (data.contains(s)) {
                    pList.add(post.toString());
                    break;
                }
            }

        }
        int totalsize = postList.size();
        if (totalsize == 0) {
            totalsize = 1;
        }
        new Statistic(String.format("Positive Percentage %.2f%%", pList.size() * 100.0 / totalsize), pList);
    }

    /**
     * update all follwers
     *
     * @param user
     */
    void updateAllFollowers(User user) {
        for (int i = 0; i < user.followersList.size(); i++) {
            User fan = user.followersList.get(i);
            int index = getIndexByUserId(fan.ID);
            if (index != -1) {

                UserView userView = userViewList.get(index);
                userView.updatePost(false);
            }
        }
        postList.clear();
        for (User u : userList) {
            for (Post m : u.posts) {
                postList.add(m);
            }
        }
        postList.sort((a, b) -> (int) (b.time - a.time));
    }

    public String dataTime(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateStr = sdf.format(date);
            return  dateStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}



