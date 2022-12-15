import java.util.Comparator;


public class SortUser implements Comparator {

    @Override
    public int compare(Object time1, Object time2) {
        User user1 = (User) time1;
        User user2 = (User) time2;
        int flag = user2.getLastUpdatetime().compareTo(user1.getLastUpdatetime());
        return flag;
    }
}
