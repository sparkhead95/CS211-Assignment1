package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;
import java.util.LinkedList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class SingleElimination implements IManager {

	//create a local array for the players in the .txt file
	private ArrayQueue totalPlayersQueue;
	//create a variable to check if the tournament has finished
	boolean queueCompleted = false;
	//local variables for players 1 and 2
	String matchedPlayer1;
    String matchedPlayer2;
	
	
	public void setPlayers(ArrayList<String> players) {
		//Instantiate the queue and define its size
		 totalPlayersQueue = new ArrayQueue(players.size());
		//For each player in players add it to the queue at the start
		 for (String player : players) {
			 totalPlayersQueue.enQ(player);
			 //System.out.println(player);
		 }
		 System.out.println("------------");
	          
	}

	public boolean hasNextMatch() {
		//If there is less than two teams left in the queue or queueCompleted is true then there cannot be a next match
		if (totalPlayersQueue.length < 2 || queueCompleted){
			return false;
		}
		else return true;
		
	}

	public Match nextMatch() throws NoNextMatchException {
		// Assign local variables for first and second current matched players
		matchedPlayer1 = (String)totalPlayersQueue.deQ();
		matchedPlayer2 = (String)totalPlayersQueue.deQ();
		// Return the match with the teams assigned to local variables
		Match match = new Match(matchedPlayer1,matchedPlayer2);
		return match;
	}

	public void setMatchWinner(boolean player1) {
		// If there are no players left in the queue
		if (totalPlayersQueue.length() == 0) {
			// Then set the competition to completed by setting the below value to true
			queueCompleted = true;
		}
		// else if the manager has set player 1 to be true then set the local variable player 1 to be first in the queue
		if (player1 == true){
			totalPlayersQueue.enQ(matchedPlayer1);
		// Otherwise set player 2 to be the winner
        }else{
        	totalPlayersQueue.enQ(matchedPlayer2);           
        }
		// Output the amount of teams in the queue
        System.out.println("Total amount of teams in the queue are: " + totalPlayersQueue.length());
	}

	public String getPosition(int n) {
		// Check the value of where the manager wants us to look
		if (n == 1){
			// if it is anything passed 0 then it must be null because only one person can win in single Elimination
			return null;
		}
		// Otherwise simply return the winner
		return (totalPlayersQueue.deQ().toString());
		
		
	}


}
