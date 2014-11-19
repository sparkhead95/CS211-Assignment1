package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;
import java.util.LinkedList;

public class SingleElimination implements IManager {

	private ArrayQueue totalPlayersQueue;
	String matchedPlayer1;
    String matchedPlayer2;
	
	
	public void setPlayers(ArrayList<String> players) {
		 totalPlayersQueue = new ArrayQueue(players.size());
		 for (String player : players) {
			 totalPlayersQueue.enQ(player);
			 System.out.println(player);
		 }
		 System.out.println("------------");
	          
	}

	public boolean hasNextMatch() {
		if (totalPlayersQueue.length < 2){
			return false;
		}
		else return true;
		
	}

	public Match nextMatch() throws NoNextMatchException {
		matchedPlayer1 = (String)totalPlayersQueue.deQ();
		matchedPlayer2 = (String)totalPlayersQueue.deQ();
		Match match = new Match(matchedPlayer1,matchedPlayer2);
		return match;
	}

	public void setMatchWinner(boolean player1) {
		// TODO Auto-generated method stub

	}

	public String getPosition(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
