import java.io.IOException;

/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */

public class Nimsys {
    private NimPlayer player1;
    private NimPlayer player2;
    private int bound;
    private int total;

    //set basic parameter of the game
    public Nimsys(int bound,int total,NimPlayer player1,NimPlayer player2){
        this.bound = bound;
        this.total = total;
        this.player1 = player1;
        this.player2 = player2;

    }

    //method used to print how many stones left
    public void display(int left){
        System.out.printf("\n" + left + " stones left:");
        for ( ; left > 0; left--) {
            System.out.print(" *");
        }
        System.out.println("");
    }

    //set last player before total stone is 0 as winner
    public void judgeWin(boolean firstPlayer){
        if (firstPlayer){
            System.out.println(player1.getFullName() + " wins!");
        }
        else {
            System.out.println(player2.getFullName() + " wins!");
        }
    }

    //print welcoming messages and collect players information
    public void toReady(){
        System.out.println("\nInitial stone count: " + total);
        System.out.println("Maximum stone removal: " + bound);
        System.out.println("Player 1: " + player1.getGivenname() + " " + player1.getFamilyname());
        System.out.println("Player 2: " + player2.getGivenname() + " " + player2.getFamilyname());
    }

    //body of game process
    public boolean startGame(){
        display(total);
        //set a boolean to judge who will play next turn
        boolean firstPlayer = true;
        while(total > 0){
            //when firstPlayer is true means it is player1's turn
            if(firstPlayer){
                System.out.println(player1.getGivenname() + "'s turn - remove how many?");
                int remove = player1.removeStone(total,bound);
                //correct input
                if(remove>0){
                    total = total - remove;
                    firstPlayer = false;
                }
            }
            else{
                System.out.println(player2.getGivenname() + "'s turn - remove how many?");
                int remove = player2.removeStone(total,bound);
                //correct input
                if(remove > 0){
                    total = total - remove;
                    firstPlayer = true;
                }
            }
            //filter the case when no stone left
            if(total > 0){
                display(total);
            }
        }
        System.out.println("\nGame Over");
        judgeWin(firstPlayer);//judge who is the winner
        return firstPlayer;
    }

    public static void main(String[] args) throws IOException {
        NimGame play = new NimGame();
        play.start(play);
    }
}
