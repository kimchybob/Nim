import java.io.IOException;

/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */

public abstract class NimPlayer {
    private String username;
    private String givenname;
    private String familyname;
    private int gamePlayed;
    private int gameWon;
    private boolean ifAI = false;

    public NimPlayer(String[] name){
        username = name[0];
        familyname = name[1];
        givenname = name[2];
        if(name.length == 5){
            gamePlayed = Integer.valueOf(name[3]);
            gameWon = Integer.valueOf(name[4]);
        }
        else{
            gamePlayed = 0;
            gameWon = 0;
        }
    }

    public NimPlayer() {}

    public void setAIPlayer(){
        ifAI = true;
    }

    public boolean getPlayerType(){
        return ifAI;
    }

    public String getUsername(){
        return username;
    }

    public String getFamilyname(){
        return familyname;
    }

    //method to get player's givenname
    public String getGivenname(){
        return givenname;
    }

    //method to get player's fullname
    public String getFullName(){
        return givenname + " " + familyname;
    }

    public void setFamilyname(String familyname){
        this.familyname = familyname;
    }

    public void setGivenname(String givenname){
        this.givenname = givenname;
    }

    //method to set times player played
    public void setGamePlayed(int gamePlayed){
        this.gamePlayed = gamePlayed;
    }

    //method to set times player won
    public void setGameWon(int gameWon){
        this.gameWon = gameWon;
    }

    //method to get times player played
    public int getGamePlayed(){
        return gamePlayed;
    }

    //method to get times player won
    public int getGameWon(){
        return gameWon;
    }

    //method to get ratio player won
    public float getRatio(){
        float ratio;
        if(gamePlayed == 0){
            ratio = 0;
        }
        else{
            ratio = 100*gameWon/gamePlayed;
        }
        return ratio;
    }

    //set return format of player
    public String toString(){
        return username + "," + givenname + "," + familyname + "," + gamePlayed + " games," +gameWon + " wins";
    }

    //method to return how many stones player want to remove
    public int removeStone(int total,int bound){
        int remove = NimGame.scanner.nextInt();
        try{
            //incorrect input
            if(remove < 1 || remove > total || remove > bound){
                throw new IOException();
            }
        }catch(IOException e){
            int min = total>bound?bound:total;
            System.out.println("\nInvalid move. You must remove between 1 and " + min + " stones.");
            remove = 0;
        }
        return remove;
    }

    public abstract String advancedMove(boolean[] available, String lastMove);
}

