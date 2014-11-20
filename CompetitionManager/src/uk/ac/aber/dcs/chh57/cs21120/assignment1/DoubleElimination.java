package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;

public class DoubleElimination implements IManager {

	private ArrayQueue winnersQueue;
	private ArrayQueue losersQueue;
	private ArrayQueue podiumQueue;
	boolean queueCompleted = false;
	String matchedPlayer1;
    String matchedPlayer2;
	
	
	public void setPlayers(ArrayList<String> players) {
		winnersQueue = new ArrayQueue(players.size());
		losersQueue = new ArrayQueue(players.size());
		 for (String player : players) {
			 winnersQueue.enQ(player);
			 //System.out.println(player);
		 }
		 System.out.println("------------");
	    
	}

	public boolean hasNextMatch() {
		if ((winnersQueue.length() < 1) && (losersQueue.length() < 1) || (queueCompleted)){
			return false;
		}
		else return true;
	}

	public Match nextMatch() throws NoNextMatchException {
		// Assign local variables for first and second current matched players
		if (losersQueue.length() == 1 && winnersQueue.length() == 1) {
			matchedPlayer1 = (String)winnersQueue.deQ();
			matchedPlayer2 = (String)losersQueue.deQ();
			queueCompleted = true;
		}
		else if (losersQueue.length() > winnersQueue.length()) {
			matchedPlayer1 = (String)losersQueue.deQ();
			matchedPlayer2 = (String)losersQueue.deQ();
		}
		else {
			matchedPlayer1 = (String)winnersQueue.deQ();
			matchedPlayer2 = (String)winnersQueue.deQ();
		}
		Match match = new Match(matchedPlayer1,matchedPlayer2);
		return match;
	}

	public void setMatchWinner(boolean player1) {
		if (winnersQueue.length() == 0) {
			queueCompleted = true;
		}
		if (player1 == true){
			winnersQueue.enQ(matchedPlayer1);
			losersQueue.enQ(matchedPlayer2);
        }else{
        	winnersQueue.enQ(matchedPlayer2); 
        	winnersQueue.enQ(matchedPlayer1); 
        }
        System.out.println("In the winners queue there are: " + winnersQueue.length());
        System.out.println("In the losers queue there are: " + losersQueue.length());

	}

	public String getPosition(int n) {
		if (queueCompleted == true) {
			switch(n){
			case 0: return (winnersQueue.deQ().toString());
			case 1: return (losersQueue.deQ().toString());
			}
		}
		else System.out.println("The game isn't finished...? Something went wrong.");
		return null;
	}

}
