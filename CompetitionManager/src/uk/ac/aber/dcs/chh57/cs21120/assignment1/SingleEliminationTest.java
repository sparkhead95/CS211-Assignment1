package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.*;

public class SingleEliminationTest {
	
	private IManager manager;
	private ArrayList<String> teams;
	
	
	
	@Before
	public void preReqs(){
		try{
			teams = CompetitionManager.readPlayers("M:\\CS211-Assignment1\\CompetitionManager\\src\\uk\\ac\\aber\\dcs\\chh57\\cs21120\\assignment1\\Teams.txt");
		}
		catch(IOException e){
			System.out.println("system error:" + e);
		}
		
		manager = IManagerFactory.getManager("uk.ac.aber.dcs.chh57.cs21120.assignment1.SingleElimination");
		manager.setPlayers(teams);
	}
	
	
	@Test
	public void testSetPlayers() {
		assertNotNull(teams);
	}

	@Test
	public void testHasNextMatch() {
		teams.clear();
		assertFalse("queue must be more than one to have a next match",manager.hasNextMatch());
	}

	@Test
	public void testNextMatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMatchWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

}
