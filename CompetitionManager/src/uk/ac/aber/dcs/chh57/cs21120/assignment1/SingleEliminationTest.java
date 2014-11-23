package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;

public class SingleEliminationTest {
	
	private IManager manager;
	private ArrayList<String> teams;
	private ArrayQueue Queue;
	private boolean endComp;
	private String player2;
	
	
	@Before
	public void preReqs() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		try{
			teams = CompetitionManager.readPlayers("C:\\Users\\Christian\\Documents\\My Work\\CS211-Assignment1\\CompetitionManager\\src\\uk\\ac\\aber\\dcs\\chh57\\cs21120\\assignment1\\Teams.txt");
		}
		catch(IOException e){
			System.out.println("system error:" + e);
		}
		
		manager = IManagerFactory.getManager("uk.ac.aber.dcs.chh57.cs21120.assignment1.SingleElimination");
		manager.setPlayers(teams);
		
		Field field1 = manager.getClass().getDeclaredField("totalPlayersQueue");
        field1.setAccessible(true);
        Queue = (ArrayQueue) field1.get(manager);
        
        Field field2 = manager.getClass().getDeclaredField("queueCompleted");
        field2.setAccessible(true);
        endComp = (boolean) field2.getBoolean(manager);
        
        Field field3 = manager.getClass().getDeclaredField("matchedPlayer2");
        field3.setAccessible(true);
        player2 = (String) field3.get(manager);
	}
	
	
	@Test
	public void testSetPlayers() {
		assertNotNull(teams);
	}

	@Test
	public void testHasNextMatch(){
		Queue.clear();
		assertFalse("queue must be more than two to have a next match", manager.hasNextMatch());
	}

	@Test
	public void testNextMatch() {
		Match m = manager.nextMatch();
		assertNotNull("Player 1 must have been assigned a team",m.getPlayer1());
		assertNotNull("Player 1 must have been assigned a team",m.getPlayer2());
		assertNotSame("Two players cannot be the same", m.getPlayer1() , m.getPlayer2());
	}

	@Test
	public void testSetMatchWinnerIfThereAreNoPlayersLeftInQueue() {
		Queue.clear();
		manager.setMatchWinner(true);
		assertFalse("QueueCompleted must be true if there are no teams left in queue, but then must be false once the winner has been added to the queue", endComp);
	}

	@Test
	public void testSetMatchWinnerIfPlayerIsFalse() {
		Queue.clear();
		manager.setMatchWinner(false);
		assertEquals("method should have returned matchedPlayer2 to be the winner", Queue.deQ().toString(), player2);
		System.out.println(player2);
	
	}
	
	
	
	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

}
