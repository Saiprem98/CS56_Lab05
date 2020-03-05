import java.util.ArrayList;

public class Lab05 {

    public static void main(String[] args) {
        MessageBoardManager messageBoard = new MessageBoardManager();

        // Create users and register tags for them.
        User u1 = new User();
        messageBoard.registerUserTag("food", u1);
        User u2 = new User();
        messageBoard.registerUserTag("cooking", u2);
        User u3 = new User();
        messageBoard.registerUserTag("FOOD", u3);

        // Constructing a Post with tags
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("food");
        tags.add("cooking");
        Post p1 = new Post(tags, "Cooking is fun!", u1, -1);
        messageBoard.addPost(p1);

        // Removing u2 from the "cooking" tag
        messageBoard.removeUserTag("cooking", u2);

        //Creating a reply to p1
        Post p1_1 = new Post(p1.getTags(), "I agree!", u3, p1.getPostID());
        messageBoard.addReply(p1_1);

        // Creating another reply to p1
        Post p1_2 = new Post(p1.getTags(), "and delicious!", u1, p1.getPostID());
        messageBoard.addReply(p1_2);

        // Creating a reply to reply p1_1
        Post p1_1_1 = new Post(p1_1.getTags(), "Mayo is gross.", u2, p1_1.getPostID());
        messageBoard.addReply(p1_1_1);

        // Displays entire thread for p1_1_1's hierarchy
        messageBoard.displayThread(p1_1_1.getPostID());

        // Displays all posts for user u1
        messageBoard.displayUserPosts(u1);

        // Creates a new post with new tags
        ArrayList<String> tags2 = new ArrayList<String>();
        tags2.add("sports");
        tags2.add("hockey");
        Post p2 = new Post(tags2, "Go Kings Go!", u3, -1);
        messageBoard.addPost(p2);

        // Displays all posts containing the tag "cooking"
        messageBoard.displayTagMessages("cooking");

        // Displays all posts containing the keyword "fun"
        messageBoard.displayKeywordMessages("fun");

        // Displays all posts
        messageBoard.displayKeywordMessages("AgrEE");

       



        // *************  Testing  *************

        // TestCase:1 No post will print 
        messageBoard.displayKeywordMessages("Basketball");
        // TestCase:2 No post will print 
        messageBoard.displayKeywordMessages("NBA");


        // TestCase:3 No post will print 
        messageBoard.displayTagMessages("Raptors");
        // TestCase:4 No post will print 
        messageBoard.displayTagMessages("Warriors");


        // TestCase:5 Will print an error
        messageBoard.removeUserTag("Warriors", u2);
        // TestCase:6 No post will print 
        messageBoard.removeUserTag("CS56", u2);


        // TestCase:7 No post will print 
         User u5 = new User();
        messageBoard.displayUserPosts(u5);


      
    }
}
