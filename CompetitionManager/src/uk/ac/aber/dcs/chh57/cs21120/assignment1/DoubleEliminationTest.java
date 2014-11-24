package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.CompetitionManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManagerFactory;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;

public class DoubleEliminationTest {

	// Create instance of the elimination method
	private IManager manager;
	// Create a local variable for the teams.txt
	private ArrayList<String> teams;
	// The below two variables are used for reflection, for when methods do not return a value that we need
	// Create local variable to store the queues that are active within the Imanager in use
	private ArrayQueue winQueue;
	private ArrayQueue lossQueue;
	// Create variable to recognise when the competition has been completed
	private boolean endComp;
	private String examplePlayer;	
	
		
	//create method to handle variables that need to be created to allow the tests to work
	@Before
	public void preReqs() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		// Attempt to store the teams.txt in a local variable. this can throw errors, so placed it in tryCatch loop
		try{
			teams = CompetitionManager.readPlayers("M:\\CS211-Assignment1\\CompetitionManager\\src\\uk\\ac\\aber\\dcs\\chh57\\cs21120\\assignment1\\Teams.txt");
		}
		catch(IOException e){
			System.out.println("system error:" + e);
		}
		
		// Define where the IManager in use is
		manager = IManagerFactory.getManager("uk.ac.aber.dcs.chh57.cs21120.assignment1.DoubleElimination");
		// Set the teams to the teams contained in the .txt we are using in the tryCatch above
		manager.setPlayers(teams);
		
		// Reflection code to get the "winnersQueue" from the IManager
		Field field1 = manager.getClass().getDeclaredField("winnersQueue");
		// Set the variable to accessible
        field1.setAccessible(true);
        // assign it to a local variable
        winQueue = (ArrayQueue) field1.get(manager);
        
        // Reflection code to get the "losersQueue" from the IManager
   		Field field2 = manager.getClass().getDeclaredField("losersQueue");
   		// Set the variable to accessible
        field2.setAccessible(true);
        // assign it to a local variable
        lossQueue = (ArrayQueue) field2.get(manager);
        
        // Reflection code to get the "queueCompleted" from the IManager
        Field field4 = manager.getClass().getDeclaredField("matchedPlayer1");
        // Set the variable to accessible
        field4.setAccessible(true);
        // assign it to a local variable
        examplePlayer = (String) field4.get(manager);
        
	}
	
	@Test
	public void testSetPlayers() {
		// Check that the program returns the correct output if it has an empty .txt file
		assertNotNull("Cant use a .txt file with no teams in it", teams);
	}
	
	@Test
	public void testSetPlayersDoesntAffectLosersQueue() {
		// Check that the program doesn't make the winners queue be the same as the losers queue at any time before the matches are set
		assertNotSame("winners can't be the same as losers before matches begin", winQueue, lossQueue);
	}

	@Test
	public void testHasNextMatchWithNoTeams() {
		// Empty the queue to put the hasNextMatch() method into the if loop for if the queue is empty, which should return that there isn't a next match
		// Therefore it must return false
		winQueue.clear();
		assertFalse("queue must be more than one to have a next match", manager.hasNextMatch());
	}

	@Test
	public void testNextMatchIfLossAndWinIsOne() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// Creates an instance of a next match and checks that the player 1 and 2 variables are assigned values
		// make it so that there is only one team in the queue so that it goes into the correct if statement
		winQueue.clear();
		winQueue.enQ(examplePlayer);
		lossQueue.clear();
		lossQueue.enQ(examplePlayer);
		manager.nextMatch();
		// Reflection code to get the "queueCompleted" from the IManager
        Field field3 = manager.getClass().getDeclaredField("queueCompleted");
        // Set the variable to accessible
        field3.setAccessible(true);
        // assign it to a local variable
        endComp = (boolean) field3.getBoolean(manager);
		assertEquals("Comp must have ended because length of both queues is 1",endComp, true);
	}

	@Test
	public void testNextMatchIsntLastMatch() {
		// Creates an  instance of a match and then checks that it is not the same as the last match
		Match m = manager.nextMatch();
		assertNotSame("Two matches cannot be the same", m, manager.nextMatch());
	}
	
	@Test
	public void testSetMatchWinner() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// Set the conditions so that the setMatchWinner goes into the correct if statement for testing
		winQueue.clear();
		manager.setMatchWinner(true);
		// Reflection code to get the "winnersQueue" from the IManager
		Field field1 = manager.getClass().getDeclaredField("winnersQueue");
		// Set the variable to accessible
		field1.setAccessible(true);
		// assign it to a local variable
		winQueue = (ArrayQueue) field1.get(manager);
		assertEquals("'winners' should still be false so the winners queue should not be touched in this if statement", winQueue.length(), 0);
	}

	@Test
	public void testGetPosition(){
		// Because the queue isnt completed, this method should return null
		assertNull("queue not complete", manager.getPosition(0));
	}

}
