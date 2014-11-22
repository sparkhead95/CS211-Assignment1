package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.CompetitionManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;

public class SingleEliminationTest {
	
	private IManager manager;
	private ArrayList<String> teams;
	
	public static ArrayList<String> readPlayers(String filename) throws FileNotFoundException {
        FileReader fr = new FileReader(filename);
        Scanner sc = new Scanner(fr);
        ArrayList<String> players = new ArrayList<String>();
        while (sc.hasNextLine()) {
            players.add(sc.nextLine());
        }
        return players;
    }
	
	@Before
	public void init(){
		try{
			teams = CompetitionManager.readPlayers("M:\\CS211-Assignment1\\CompetitionManager\\src\\uk\\ac\\aber\\dcs\\chh57\\cs21120\\assignment1\\Teams.txt");
		}
		catch(IOException e){
			System.out.println("system error:" + e);
		}
		
		
		
	}
	
	
	@Test
	public void testSetPlayers(ArrayList<String> players) {
		//classTest.setPlayers(players);
		assertNotNull(players);
	}

	@Test
	public void testHasNextMatch() {
		fail("Not yet implemented");
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
