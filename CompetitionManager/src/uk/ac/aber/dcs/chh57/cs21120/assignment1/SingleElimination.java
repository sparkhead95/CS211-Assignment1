package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;
import java.util.LinkedList;

public class SingleElimination implements IManager {

	private ArrayQueue totalPlayersQueue;
	
	
	
	public void setPlayers(ArrayList<String> players) {
		 totalPlayersQueue = new ArrayQueue(players.size());
		 for (String player : players) {
			 totalPlayersQueue.enQ(player);
			 System.out.println(player);
		 }
	          


	}

	public boolean hasNextMatch() {
		// TODO Auto-generated method stub
		return false;
	}

	public Match nextMatch() throws NoNextMatchException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMatchWinner(boolean player1) {
		// TODO Auto-generated method stub

	}

	public String getPosition(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
