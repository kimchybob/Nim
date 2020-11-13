/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */

public class AdvancedGame {
    private NimPlayer player1;
    private NimPlayer player2;
    private int total;
    private boolean[] available;
    private String lastMove = "0 0";

    //set basic parameter of the game
    public AdvancedGame(int total,NimPlayer player1,NimPlayer player2){
        this.total = total;
        this.player1 = player1;
        this.player2 = player2;
        available = new boolean[total];
        for(int round = 0;round < total;round ++){
            available[round] = true;
        }
    }

    //method used to print how many stones left
    public void display(int left){
        System.out.printf("\n" + left + " stones left:");
        for(int round = 1;round <= available.length;round ++){
            System.out.printf(" <%d,", round);
            if(available[round - 1]){
                System.out.print("*>");
            }
            else{
                System.out.print("x>");
            }
        }
        System.out.println("");
    }

    //set player took last stone as winner
    public void judgeWin(boolean firstPlayer){
        if (!firstPlayer){
            System.out.println(player1.getFullName() + " wins!");
        }
        else {
            System.out.println(player2.getFullName() + " wins!");
        }
    }

    //print welcoming messages and collect players information
    public void toReady(){
        System.out.println("\nInitial stone count: " + total);
        System.out.printf("Stones display:");
        for(int round = 1;round <= available.length;round ++){
            System.out.printf(" <%d,*>", round);
        }
        System.out.println("");
        System.out.println("Player 1: " + player1.getGivenname() + " " + player1.getFamilyname());
        System.out.println("Player 2: " + player2.getGivenname() + " " + player2.getFamilyname());
    }

    //body of game process
    public boolean startGame() {
        display(total);
        //set a boolean to judge who will play next turn
        boolean firstPlayer = true;
        while (total > 0) {
            //when firstPlayer is true means it is player1's turn
            if (firstPlayer) {
                System.out.println(player1.getGivenname() + "'s turn - which to remove?");
                String move = player1.advancedMove(available, lastMove);
                String[] moveArray = move.split(" ");
                int index = Integer.parseInt(moveArray[0]);
                int remove = Integer.parseInt(moveArray[1]);
                //correct input
                if (index > 0) {
                    total = total - remove;
                    available[index - 1] = false;
                    available[index + remove - 2] = false;
                    firstPlayer = false;
                    lastMove = move;
                }
            }
            else {
                System.out.println(player2.getGivenname() + "'s turn - which to remove?");
                String move = player2.advancedMove(available, lastMove);
                String[] moveArray = move.split(" ");
                int index = Integer.parseInt(moveArray[0]);
                int remove = Integer.parseInt(moveArray[1]);
                //correct input
                if (index > 0) {
                    total = total - remove;
                    available[index - 1] = false;
                    available[index + remove - 2] = false;
                    firstPlayer = true;
                    lastMove = move;
                }
            }
            //filter the case when no stone left
            if (total > 0) {
                display(total);
            }
        }
        System.out.println("\nGame Over");
        judgeWin(firstPlayer);//judge who is the winner
        return firstPlayer;
    }
}
