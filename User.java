import java.util.ArrayList;
import java.util.LinkedHashMap;

public class User implements Observer{
    public static int userID = 0;
    private LinkedHashMap<User, Integer> tagToUser = new LinkedHashMap<User, Integer>();
    public User(){
        tagToUser.put(this,++userID);

    }


    public int getUserID(User u){
        return tagToUser.get(u);
    }

    @Override
    public void update(Post post) {
        if(post.getParentID() == -1) {
            System.out.println("--");
            System.out.println("Post ID: " + post.getPostID());
            System.out.print("Tags: ");
            ArrayList<String> tags = post.getTags();

            for (int i = 0; i < tags.size(); i++) {
                System.out.print(tags.get(i));
                System.out.print(" ");
            }
            System.out.println("");
            System.out.println("Posted by UserID: " + post.getUserPostID());
            System.out.println("Message: " + post.getMessage());
            System.out.println("--");

        }
        else{
            System.out.println("--");
            System.out.println("Post ID: " + post.getPostID());
            System.out.print("Tags: ");
            ArrayList<String> tags = post.getTags();
            for (int i = 0; i < tags.size(); i++) {
                System.out.print(tags.get(i));
                System.out.print(" ");
            }
            System.out.println("");
            System.out.println("Posted by UserID: " + post.getUserPostID());
            System.out.println("Re: to Post ID: " + post.getParentID());
            System.out.println("Message: " + post.getMessage());
            System.out.println("--");


        }
    }
}
