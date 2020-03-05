import java.lang.reflect.Array;
import java.util.ArrayList;
//import javafx.util.Pair;
import java.util.List;
import java.util.LinkedHashMap;

public class MessageBoardManager implements  Subject {

    private ArrayList<Observer> observers;
    private Pair<String, User> tagToUser1;
    private ArrayList<Pair<String, User>> pairs = new ArrayList<Pair<String, User>>();
    private LinkedHashMap<Integer, User> tagToUser = new LinkedHashMap<Integer, User>();
    private ArrayList<Pair<User, Post>> postToUser = new ArrayList<Pair<User,Post>>();
    private ArrayList<Pair<Integer, Integer>> idParent = new ArrayList<Pair<Integer,Integer>>();

    public MessageBoardManager() {
        observers = new ArrayList<Observer>();
    }
    public ArrayList<Pair<String, User>> returnPairList(){
        return pairs;
    }
    public ArrayList<Pair<User, Post>> returnList(){
        return postToUser;
    }
    public void addPost(Post p) {
        postToUser.add(new Pair<User,Post>(p.getUser(), p));
        idParent.add(new Pair<Integer, Integer>(p.getPostID(), p.getParentID()));
        if (p.getParentID() == -1) {
            System.out.println("+++ Adding Post to MessageBoard +++");
            System.out.println("--");
            System.out.println("Post ID: " + p.getPostID());
            System.out.print("Tags: ");
            ArrayList<String> tags = p.getTags();

            for (int i = 0; i < tags.size(); i++) {
                System.out.print(tags.get(i));
                System.out.print(" ");
            }
            System.out.println("");

            System.out.println("Posted by UserID: " + p.getUser().getUserID(p.getUser()));
            System.out.println("Message: " + p.getMessage());
            System.out.println("--");
            System.out.println("++++++++++++++++++++++++++++++++++++");
            System.out.println("");
        }
        notifyUsers(p);
        System.out.println("");
    }

    public void addReply(Post reply) {
        postToUser.add(new Pair<User,Post>(reply.getUser(), reply));
        idParent.add(new Pair<Integer, Integer>(reply.getPostID(), reply.getParentID()));
        System.out.println("+++ Adding Post to MessageBoard +++");
        System.out.println("--");
        System.out.println("Post ID: " + reply.getPostID());
        System.out.print("Tags: ");
        ArrayList<String> tags = reply.getTags();
        for (int i = 0; i < tags.size(); i++) {
            System.out.print(tags.get(i));
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("Posted by UserID:" + reply.getUserPostID());
        System.out.println("Re: to Post ID: " + reply.getParentID());
        System.out.println("Message: " + reply.getMessage());
        System.out.println("--");
        System.out.println("++++++++++++++++++++++++++++++++++++");
        notifyUsers(reply);
        System.out.println("");
    }

    public void displayTagMessages(String tag) {
        System.out.println("##### Displaying posts with tag: " + tag + " #####");
        for(int i =0; i<postToUser.size();i++){
            ArrayList<String> check = postToUser.get(i).getSecond().getTags();
                for(int k = 0; k<check.size() ; k++){
                    if(check.get(k).equals(tag)){
                        postToUser.get(i).getFirst().update(postToUser.get(i).getSecond());
                    }
                }
        }
        System.out.println("##############################");
    }

    public void displayKeywordMessages(String keyword) {
        System.out.println("##### Displaying posts with keyword: "+ keyword + " #####");
        for(int i =0; i<postToUser.size();i++){
            String[] words = postToUser.get(i).getSecond().getWords();
            for(int k =0; k<words.length;k++){
                //System.out.println(i+" "+words[k]);

                words[k] = words[k].replace("!", "");
                words[k] = words[k].toLowerCase();
               // System.out.println(i+" "+words[k]);
                if(words[k].equals(keyword.toLowerCase())){
                    //System.out.println("HERE");
                    postToUser.get(i).getFirst().update(postToUser.get(i).getSecond());
                }
            }
        }
        System.out.println("##############################");
    }

    public void displayThread(int postID) {
        System.out.println("##### Displaying thread for PostID: " + postID + " #####");
        ArrayList<Pair<User, Post>> posts = new ArrayList<Pair<User, Post>>();
        ArrayList<String> tags = new ArrayList<String> ();
        for (int i =0; i<postToUser.size();i++){
            posts.add(postToUser.get(i));
        }
        for (int i = 0; i<postToUser.size();i++){
            if(postToUser.get(i).getSecond().getPostID() == postID){
                tags = postToUser.get(i).getSecond().getTags();
            }
        }
        int currentPostID = 0;
        int previousPostID = 0;
        int count =0;
       for (int i =0; i<posts.size();i++){
           if(posts.get(i).getSecond().getParentID() == -1 && tags ==  postToUser.get(i).getSecond().getTags()){
               posts.get(i).getFirst().update(posts.get(i).getSecond());
               count++;
               currentPostID =  posts.get(i).getSecond().getPostID();
               previousPostID =  posts.get(i).getSecond().getPostID();
               posts.remove(posts.get(i));
           }

       }

       boolean keepSearching = true;
        while(keepSearching){
            for (int i =0; i<posts.size();i++){
                if(posts.get(i).getSecond().getParentID() == currentPostID && tags ==  postToUser.get(i).getSecond().getTags()){
                    posts.get(i).getFirst().update(posts.get(i).getSecond());
                    count++;
                    currentPostID =  posts.get(i).getSecond().getPostID();
                    posts.remove(posts.get(i));
                    i = 0;
                }
                if(i ==  posts.size() -1 ){

                    keepSearching = false;
                }
            }
        }
//        currentPostID = currentPostID - count;
//        System.out.println(previousPostID+" " + currentPostID);
        boolean keep = true;
        while(keep){
            for (int i =0; i<posts.size();i++){
                if(posts.get(i).getSecond().getParentID() == previousPostID || posts.get(i).getSecond().getParentID() == currentPostID){
                        if(tags ==  postToUser.get(i).getSecond().getTags()) {
                            previousPostID++;
                            posts.get(i).getFirst().update(posts.get(i).getSecond());
                            count++;
                            currentPostID = posts.get(i).getSecond().getPostID();
                            posts.remove(posts.get(i));
                            i = 0;
                        }
                }
                if(i ==  posts.size() - 1 || i == 0 ){
                    keep = false;
                }
            }
        }
        System.out.println("##############################");
        System.out.println("");

    }

    public void displayUserPosts(User user) {
        System.out.println("##### Displaying all posts for User ID:" + user.getUserID(user) + " #####");
        for(int i =0; i<postToUser.size();i++){
            if(postToUser.get(i).getFirst() == user){
                user.update(postToUser.get(i).getSecond());
            }
        }
        System.out.println("##############################");
        System.out.println("");
    }

    @Override
    public void registerUserTag(String tag, User user) {
      //  tagToUser.put(user.getUserID(), user);
        String lowercase = tag.toLowerCase();
        //Pair<String,User> p1 = new Pair<String,User> (lowercase,user);
        pairs.add(new Pair<String, User>(lowercase, user));
        observers.add(user);
        System.out.println("^^^^^ Adding tag: " + tag + " for User ID: " + user.getUserID(user) + " ^^^^^");
        System.out.println("");
    }

    @Override
    public void removeUserTag(String tag, User user) {
        // boolean found = false;
        // ArrayList<String> tags = new ArrayList<String> ();
        // for (int i = 0; i<postToUser.size();i++){
        //     if(postToUser.get(i).getSecond().getUser() == user){
        //         tags = postToUser.get(i).getSecond().getTags();
        //     }
        // }
        // for (int i = 0; i<tags.size(); i++){
        //     if(tags.get(i).equals(tag)){
        //         found = true;
        //     }
        // }
        // if(found){
       // System.out.println(" ^^^^^ Removing tag: cooking for User ID: " + user.getUserID(user) + " ^^^^^");
       
        for (int i = 0; i < pairs.size(); i++) {
            if(pairs.get(i).getFirst().equalsIgnoreCase(tag)){
               System.out.println(" ^^^^^ Removing tag: cooking for User ID: " + user.getUserID(user) + " ^^^^^");
                pairs.remove(i);
                 System.out.println("");
                return;
            }
        }
        System.out.println("Error: removeUserTag incorret tag or user");
        System.out.println("");
       // }
    }

    @Override
    public void notifyUsers(Post p) {
        ArrayList<String> tags = p.getTags();
        for (int i = 0; i < tags.size(); i++) {
            for(int k = 0; k<pairs.size();k++) {
                if (tags.get(i).equals(pairs.get(k).getFirst())) {
                    if(pairs.get(k).getSecond().getUserID(pairs.get(k).getSecond()) != p.getUserPostID()){
                    System.out.println("***** Updating UserID: " + pairs.get(k).getSecond().getUserID(pairs.get(k).getSecond())+" *****");
                    pairs.get(k).getSecond().update(p);
                    System.out.println("******************************");
                      //  System.out.println("");
                     }
                }
            }
        }
    }
}
