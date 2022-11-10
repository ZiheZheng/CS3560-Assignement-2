public class Post {

    /**
     * Post's ID
     */
    String postID;

    /**
     * post message
     */
    String data;

    /**
     * Record time
     */
    long time;

    /**
     * Constructor for post
     * @param postID
     * @param data
     */
    public Post(String postID, String data) {
        this.postID = postID;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    /**
     * Getter and Setter
     * @return
     */
    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getData() {
        return data;
    }

    public Comparable<Long> getTime() {
        return time;
    }
    

    @Override
    public String toString()
    {
        return String.format(" - %s:%s", postID, data);
    }

}
