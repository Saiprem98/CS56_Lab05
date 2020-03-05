import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class MessageBoardTester {
	
	//// *************** Was Able to Test More in Main **************** //////////
	@Test
	public void testAddPost(){
		MessageBoardManager test = new MessageBoardManager();
		ArrayList<Pair<User, Post>> testList = new ArrayList<Pair<User,Post>>();
		testList = test.returnList();
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
        test.registerUserTag("Basketball", u3);

        // Constructing a Post with tags
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Basketball");
        tags.add("Sports");
        Post p1 = new Post(tags, "NBA Finals", u1, -1);
         test.addPost(p1);
        Post p2 = new Post(tags, "Warriors", u2, -1);
        test.addPost(p2);
        Post p3 = new Post(tags, "Raptors", u3, -1);
        test.addPost(p3);
        testList = test.returnList();

        if(testList.size() != 3){
        	fail();
        }
        else{
        	return;
        }
	}
	@Test
	public void testAddPostContents(){
		MessageBoardManager test1 = new MessageBoardManager();
		ArrayList<Pair<User, Post>> testList = new ArrayList<Pair<User,Post>>();
		testList = test1.returnList();
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
        test1.registerUserTag("Basketball", u3);

        // Constructing a Post with tags
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Basketball");
        tags.add("Sports");
        Post p1 = new Post(tags, "NBA Finals", u1, -1);
         test1.addPost(p1);
        Post p2 = new Post(tags, "Warriors", u2, -1);
        test1.addPost(p2);
        Post p3 = new Post(tags, "Raptors", u3, -1);
        test1.addPost(p3);
        testList = test1.returnList();

        int testCount = 0;
        if(testList.get(0).getSecond().getMessage().equals("NBA Finals")){
        	
        	testCount++;
        }
        if(testList.get(1).getSecond().getPostID() == 5 ){
        	
        	testCount++;
        }
        if(testList.get(2).getSecond().getParentID() == -1){
        	
        	testCount++;
        }
        if(testCount == 3){
        	return;
        }
        else{
        	fail();
        }

	}

	@Test
	public void testAddReply(){
				MessageBoardManager test2 = new MessageBoardManager();
		ArrayList<Pair<User, Post>> testList = new ArrayList<Pair<User,Post>>();
		testList = test2.returnList();
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
        test2.registerUserTag("Basketball", u3);

        // Constructing a Post with tags
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("Basketball");
        tags.add("Sports");
        Post p1 = new Post(tags, "NBA Finals predictions ?", u1, -1);
         test2.addPost(p1);
        Post p2 = new Post(tags, "Warriors in 6 ", u2, 1);
        test2.addPost(p2);
        Post p3 = new Post(tags, "No! Raptors in 7", u3, 2);
        test2.addPost(p3);
        testList = test2.returnList();

        int testCount = 0;
        if(testList.get(1).getSecond().getParentID() == 1 ){
        	
        	testCount++;
        }
       	if(testList.get(2).getSecond().getParentID() == 2 ){
        	
        	testCount++;
        }
        if(testCount == 2){
        	return;
        }
        else{
        	fail();
        }

	}

	@Test
	public void testRemoveTags(){

		MessageBoardManager test3 = new MessageBoardManager();
		ArrayList<Pair<String, User>> testList = new ArrayList<Pair<String, User>>();
		testList = test3.returnPairList();
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
        test3.registerUserTag("Basketball", u1);
        test3.registerUserTag("Basketball", u2);
        test3.registerUserTag("Basketball", u3);

        test3.removeUserTag("Basketball", u1);
        test3.removeUserTag("Basketball", u2);

        if(testList.size() == 1){
        	return;
        }
        else{
        	fail();
        }
       
	}
}
 // javac -cp .:* MessageBoardTester.java Lab05.java
 // java -cp .:* org.junit.runner.JUnitCore MessageBoardTester