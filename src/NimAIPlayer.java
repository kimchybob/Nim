/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */
public class NimAIPlayer extends NimPlayer implements Testable{
	// you may further extend a class or implement an interface
	// to accomplish the tasks.

	public NimAIPlayer(){
		super();
		setAIPlayer();
	}

	public NimAIPlayer(String[] name) {
		super(name);
		setAIPlayer();
	}

	//method to return how many stones player want to remove
	@Override
	public int removeStone(int total,int bound){
		int remove = total % (bound + 1) - 1;
		if (remove < 0) {
			remove = bound;
		}
		else if(remove == 0){
			remove = 1;
		}
		return remove;
	}

	//combine index and remove stone number as String
	public static String combine(int index,int moveNum){
		String move = String.valueOf(index) + " " + String.valueOf(moveNum);
		return move;
	}


	@Override
	public String advancedMove(boolean[] available, String lastMove) {
		String move = "";
		String[] lastMoveArray = lastMove.split(" ");
		if(lastMove.equals("")){
			if(available[(available.length + 1) / 2 - 1]){
				//even
				if(available.length % 2 == 0 && available[(available.length + 1) / 2]){
					move = combine((available.length + 1) / 2,2);
				}
				//odd
				else{
					move = combine((available.length + 1) / 2,1);

				}
			}
			return move;
		}
		//concept index
		int index = Integer.parseInt(lastMoveArray[0]);
		int moveNum = Integer.parseInt(lastMoveArray[1]);
		int oppIndex = Math.abs(index-available.length-1);
		int period = (available.length + 1) / 2;//6
		//first step: took the mid stone
		if(available[period - 1]){
			//even
			if(available.length % 2 == 0 && available[period]){
				move = combine(period,2);
			}
			//odd
			else{
				move = combine(period,1);

			}
		}
		else if (moveNum == 2){
			//if correspond position is unavailable
			if(!available[oppIndex - 1] && !available[oppIndex - 2]){
				int round = 0;
				while(!available[round]){
					round++;
				}
				//take stone from first available position from the array
				if(available[round + 1]){
					move = combine(round + 1,2);
				}
				else{
					move = combine(round + 1,1);
				}
			}
			//opposite side has one position unavailable
			else if(!available[oppIndex - 1] || !available[oppIndex - 2]){
				move = combine(available[oppIndex - 1]?oppIndex:(oppIndex - 1), 1);
			}
			//both is available
			else{
				move = combine(oppIndex - 1,2);
			}
		}
		else if(moveNum == 1){
			//opposite side is unavailable
			if(!available[oppIndex - 1]){
				int round = 0;
				while(!available[round]){
					round++;
				}
				//opposite side has one position unavailable
				if(available[round + 1]){
					move = combine(round + 1,2);
				}
				else{
					move = combine(round + 1,1);
				}
			}
			//opposite is available
			else{
				//lastmove is at position 1 or 10
				if(index == 1){
					if(!available[index] && available[oppIndex - 2]){
						move = combine(oppIndex - 1,2);
					}
					else{
						move = combine(oppIndex,1);
					}
				}
				else if(index == available.length){
					if(!available[index - 2] && available[oppIndex]){
						move = combine(oppIndex,2);
					}
					else{
						move = combine(oppIndex,1);
					}
				}
				//index right is available but opposite left is not
				else if(!available[index] && available[Math.abs(index - available.length) - 1]){
					move = combine(Math.abs(index - available.length),2);
				}
				//index left is available but opposite right is not
				else if(!available[index - 2] && available[oppIndex]){
					move = combine(oppIndex,2);
				}
				else{
					move = combine(oppIndex,1);
				}
			}
		}
		return move;
	}
}
