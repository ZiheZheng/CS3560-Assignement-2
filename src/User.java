import java.util.ArrayList;
import java.util.List;

public class User {



    String creatingTime;

    String lastUpdatetime;

    /**
     * User's ID
     */
    String ID;

    /**
     * User's followers list
     */
    List<User> followersList;

    /**
     * User's followings list
     */
    List<User> followingsList;

    /**
     * User's Post
     */
    List<Post> posts;

    public String getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(String creatingTime) {
        this.creatingTime = creatingTime;
    }

    public String getLastUpdatetime() {
        return lastUpdatetime;
    }

    public void setLastUpdatetime(String lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }



    /**
     * User's constructor
     * @param ID
     */
    public User(String ID, String creatingTime, String updateTime) {
        this.ID = ID;
        this.creatingTime = creatingTime;
        this.lastUpdatetime = updateTime;
        followersList = new ArrayList<>();
        followingsList = new ArrayList<>();
        posts = new ArrayList<>();
    }

    /**
     * function to add post to Object Post
     * @param data
     */
    public void postMessage(String data) {
        Post post = new Post(ID, data);
        posts.add(post);
    }

    /**
     * Function that allow user to follow another user
     * @param user
     */
    public void follow(User user) {

        user.followersList.add(this);
        this.followingsList.add(user);

    }


    @Override
    public String toString() {
        return  ID + " last update time " + lastUpdatetime;
    }
}
