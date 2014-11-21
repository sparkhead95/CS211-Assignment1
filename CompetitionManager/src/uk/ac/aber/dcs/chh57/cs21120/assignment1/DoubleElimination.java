package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;

public class DoubleElimination implements IManager {

	private ArrayQueue winnersQueue;
	private ArrayQueue losersQueue;
	private ArrayQueue myQueue;
	boolean queueCompleted = false;
	String matchedPlayer1;
    String matchedPlayer2;
    String posPlayer1 = "";
	String posPlayer2 = "";
	boolean winners;
	
	
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
		if ((winnersQueue.length() < 1) || (queueCompleted)){
			return false;
		}
		else return true;
	}

	public Match nextMatch() throws NoNextMatchException {
		// Assign local variables for first and second current matched players
        if (losersQueue.length() == 1 && winnersQueue.length() == 1) {
                matchedPlayer1 = (String)winnersQueue.deQ();
                //posPlayer1 = "winner";
                matchedPlayer2 = (String)losersQueue.deQ();
                //posPlayer2 = "loser";
                queueCompleted = true;
                winners=true;
        }
        else if (winnersQueue.length() > losersQueue.length()) {
                //myQueue = winnersQueue;
                matchedPlayer1 = (String)winnersQueue.deQ();
                //posPlayer1 = "loser";
                matchedPlayer2 = (String)winnersQueue.deQ();
                winners=true;
                //posPlayer2 = "loser";
        }
        else {
                //myQueue = losersQueue;
                matchedPlayer1 = (String)losersQueue.deQ();
                //posPlayer1 = "winner";
                matchedPlayer2 = (String)losersQueue.deQ();
                //posPlayer2 = "winner";
                winners=false;
        }
        Match match = new Match(matchedPlayer1,matchedPlayer2);
        return match;
}

	public void setMatchWinner(boolean player1) {
		if (winners) {
			if (player1) {
				winnersQueue.enQ(matchedPlayer1);
				losersQueue.enQ(matchedPlayer2);
			} else {
				winnersQueue.enQ(matchedPlayer2);
				losersQueue.enQ(matchedPlayer1);
			}
		} else {
			if (player1) {
				losersQueue.enQ(matchedPlayer1);

			} else {
				losersQueue.enQ(matchedPlayer2);

			}
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
