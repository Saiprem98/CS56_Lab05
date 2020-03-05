import java.util.ArrayList;
import java.util.LinkedHashMap;

//A list of Strings representing the post’s tags.
//        A String containing the message of the post.
//        A list of posts that are replies for this post.
//        An int representing a unique Post ID. The Post IDs are unique positive numbers starting at 1 and incrementally increases every time a new Post in the system is created.
//        An int representing a Parent Post ID. If the post is not a reply to an existing post, then this is set to -1.
//        If the post is a reply to an existing post, then this Parent Post ID is the Post ID of the message this post is replying to.
//        If the post is a reply to an existing post, no additional tags will be added to this post. However, the reply post will contain the same tags as the post it is replying to.
//        A reference to a User object who made the post.
//        Any accessor / mutator methods or other fields that your code needs in order to manage posts in the system.

public class Post{

    private static int postID = 0;
    private String postTag;
    private  int userPostID;
    private  String message;
    private  int parentID;
    private User postUser;
    String[] words;
    private LinkedHashMap<Post, Integer> tagToUser = new LinkedHashMap<Post, Integer>();

    ArrayList<String> tags = new ArrayList<String> ();

    public Post(ArrayList<String> tags, String message, User user, int parentID) {
        this.parentID = parentID;
        this.message = message;
        this.tags = tags;
        userPostID = user.getUserID(user);
        postUser = user;
        tagToUser.put(this,++postID);
         words = message.split(" ");

    }

    public User getUser(){
        return postUser;
    }

    public String[] getWords(){
        return words;
    }

    public String getMessage(){
        return message;
    }

    public int getUserPostID(){
        return userPostID;
    }
    public int getParentID(){
        return parentID;
    }

    public ArrayList<String> getTags(){

        return tags;
    }

    public int getPostID(){
        return tagToUser.get(this);
    }
}
