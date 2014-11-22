package uk.ac.aber.dcs.chh57.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class DoubleElimination implements IManager {

	//create the two queues that the teams will be placed in
	private ArrayQueue winnersQueue;
	private ArrayQueue losersQueue;
	//create the variable to define whether the competition has finished
	boolean queueCompleted = false;
	//local variables for the teams playing
	String matchedPlayer1;
    String matchedPlayer2;
    //boolean to define which queue the team was in before it is decided where they go after the match
	boolean winners;
	
	
	public void setPlayers(ArrayList<String> players) {
		//Instantiate the queues and define their size
		winnersQueue = new ArrayQueue(players.size());
		losersQueue = new ArrayQueue(players.size());
		//For each player in players add it to the winners queue at the start
		 for (String player : players) {
			 winnersQueue.enQ(player);
			 //System.out.println(player);
		 }
		 System.out.println("------------");
	    
	}

	public boolean hasNextMatch() {
		//If there is only one team left in the queue or queueCompleted is true then there cannot be a next match
		if ((winnersQueue.length() < 1) || (queueCompleted)){
			return false;
		}
		else return true;
	}

	public Match nextMatch() throws NoNextMatchException {
		//Place the final team for each queue in a match together if there are no other teams in each queue
        if (losersQueue.length() == 1 && winnersQueue.length() == 1) {
                matchedPlayer1 = (String)winnersQueue.deQ();
                matchedPlayer2 = (String)losersQueue.deQ();
                //After this match the queueCompleted must be true which ends the competition
                queueCompleted = true;
                //Defines that the result team is a winner
                winners=true;
        }
        //If there are more players in the winners queue then make two from the winners queue have a match
        else if (winnersQueue.length() > losersQueue.length()) {
                matchedPlayer1 = (String)winnersQueue.deQ();
                matchedPlayer2 = (String)winnersQueue.deQ();
                //Defines that the result team is a winner
                winners=true;
        }
        // Otherwise choose two from the losers queue to play
        else {
                matchedPlayer1 = (String)losersQueue.deQ();
                matchedPlayer2 = (String)losersQueue.deQ();
                //Defines that the result team is a winner
                winners=false;
        }
        //Create the match and put the teams in.
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
