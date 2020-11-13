/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */

import java.io.*;
import java.util.Scanner;

public class NimGame {
    public static Scanner scanner = new Scanner(System.in);
    private NimPlayer[] players = new NimPlayer[100];

    //print welcoming message
    public void welcome(){
        System.out.println("Welcome to Nim");
    }

    public void readData() throws IOException{
        try{
            //search for file name players.dat
            FileInputStream inputStream = new FileInputStream("players.dat");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            int round = 0;
            //read lines from dat file
            while((str = bufferedReader.readLine()) != null)
            {
                String[] inputArray = str.split(",");
                String[] gamePlayed = inputArray[3].split(" ");
                String[] gameWon = inputArray[4].split(" ");
                //adjust format
                String[] nameArray = {inputArray[0],inputArray[2],inputArray[1],gamePlayed[0],gameWon[0]};
                //judge if player is AI
                if(inputArray[5].equals("true")){
                    players[round] = new NimAIPlayer(nameArray);

                }
                else{
                    players[round] = new NimHumanPlayer(nameArray);
                }
                round ++;
            }

            //close
            inputStream.close();
            bufferedReader.close();
        }catch(IOException e){
            File newFile = new File("players.dat");
            newFile.createNewFile();
        }
    }

    //print $ and wait for input command
    public String[] commandInput(){
        System.out.print("\n$");
        String input = scanner.nextLine();
        String[] command = input.split(" ");
        return command;
    }

    public void addPlayer(String command){
        //username,family_name,given_name
        String[] nameArray = command.split(",");
        try{
            if(nameArray.length<3){
                throw new IOException();
            }
            else{
                int round = 0;
                //the player exists
                for(;round < 99 && players[round] != null;round ++) {
                    if (nameArray[0].equals(players[round].getUsername())) {
                        System.out.println("The player already exists.");
                        break;
                    }
                }//create new player
                players[round] = new NimHumanPlayer(nameArray);
                }
        }catch(IOException e){
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public void addAIPlayer(String command){
        //username,family_name,given_name
        String[] nameArray = command.split(",");
        try{
            if(nameArray.length<3){
                throw new IOException();
            }
            else{
                int round = 0;
                //the player exists
                for(;round < 99 && players[round] != null;round ++) {
                    if (nameArray[0].equals(players[round].getUsername())) {
                        System.out.println("The player already exists.");
                        break;
                    }
                }//create new AI player
                players[round] = new NimAIPlayer(nameArray);
            }
        }catch(IOException e){
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public void removePlayer(String[] command){
        //remove all players
        if(command.length == 1){
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String answer = scanner.nextLine();
            //remove all players
            if (answer.equals("Y")|| answer.equals("y")){
                for(int round = 0;round < 99 && players[round] != null;round ++){
                    players[round] = null;
                }

            }
        }
        else{
            boolean existence = false;
            for(int round = 0;round < 99 && players[round] != null;round ++){
                //if exist
                if (command[1].equals(players[round].getUsername())) {
                    existence = true;
                    for(int index = round;index < 99 && players[index] != null;index++){
                        players[index] = players[index + 1];
                    }
                    break;
                }
            }
            //do not exist
            if (! existence){
                System.out.println("The player does not exist.");
            }
        }
    }

    public void editPlayer(String command){
        String[] nameArray = command.split(",");//username,family_name,given_name
        try{
            if(nameArray.length<3){
                throw new IOException();
            }
            else{
                boolean existence = false;
                for(int round = 0;round < 99 && players[round] != null;round ++) {
                    //if found the username
                    if (nameArray[0].equals(players[round].getUsername())) {
                        existence = true;
                        //change familyname and givenname
                        players[round].setFamilyname(nameArray[1]);
                        players[round].setGivenname(nameArray[2]);
                        break;
                    }
                }
                //do not exist
                if (! existence){
                    System.out.println("The player does not exist.");
                }
            }
        }catch(IOException e){
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public void resetStats(String[] command){
        //reset all players
        if(command.length == 1){
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String answer = scanner.nextLine();//
            if (answer.equals("Y")|| answer.equals("y")){
                for(int round = 0;round < 99 && players[round] != null;round ++){
                    players[round].setGamePlayed(0);
                    players[round].setGameWon(0);
                }

            }
        }
        else{
            //find if player exist
            boolean existence = false;
            for(int round = 0;round < 99 && players[round] != null;round ++){
                //if exist
                if (command[1].equals(players[round].getUsername())) {
                    existence = true;
                    players[round].setGamePlayed(0);
                    players[round].setGameWon(0);
                    break;
                }
            }
            //do not exist
            if (! existence){
                System.out.println("The player does not exist.");
            }
        }

    }

    //use bubble sort to sort players' name alphabetically
    public void sortAlphabetically(){
        for(int loIndex = 0;loIndex < 99 && players[loIndex + 1] != null ; loIndex++){
            for(int hiIndex = loIndex + 1;hiIndex < 99 && players[hiIndex] != null ; hiIndex++)
                if(players[loIndex].getUsername().compareTo(players[hiIndex].getUsername()) > 1){
                    NimPlayer temp;
                    temp = players[hiIndex];
                    players[hiIndex] = players[loIndex];
                    players[loIndex] = temp;
                }
        }
    }

    public void displayPlayer(String[] command){
        //display all players
        if(command.length == 1){
            //sort according to the name first
            sortAlphabetically();
            for(int round = 0;round < 99 && players[round] != null;round ++){
                System.out.println(players[round]);
            }
        }
        else{
            boolean existence = false;
            for(int round = 0;round < 99 && players[round] != null;round ++){
                //if exist
                if (command[1].equals(players[round].getUsername())) {
                    existence = true;
                    System.out.println(players[round]);
                    break;
                }
            }
            //do not exist
            if (! existence){
                System.out.println("The player does not exist.");
            }
        }
    }

    public void startGame(String command){
        String[] gameParameter = command.split(",");
        try{
            if(gameParameter.length < 4){
                throw new IOException();
            }
            else{
                boolean playerOneExistence = false;
                boolean playerTwoExistence = false;
                int playerOneIndex = 0;
                int playerTwoIndex = 0;
                //search for both two players
                for(int round = 0;round < 99 && players[round] != null;round ++){
                    //player one exist
                    if(gameParameter[2].equals(players[round].getUsername())){
                        gameParameter[2] = players[round].getGivenname() + " " + players[round].getFamilyname();
                        playerOneExistence = true;
                        playerOneIndex = round;
                    }
                    //player two exist
                    if(gameParameter[3].equals(players[round].getUsername())){
                        gameParameter[3] = players[round].getGivenname() + " " + players[round].getFamilyname();
                        playerTwoExistence  = true;
                        playerTwoIndex = round;
                    }
                }
                //if both players exist
                if(playerOneExistence && playerTwoExistence){
                    int total = Integer.parseInt(gameParameter[0]);
                    int bound = Integer.parseInt(gameParameter[1]);
                    //create object Nimsys, transfer essential parameters
                    Nimsys game = new Nimsys(bound,total,players[playerOneIndex],players[playerTwoIndex]);
                    //change game status of two players
                    players[playerOneIndex].setGamePlayed(players[playerOneIndex].getGamePlayed() + 1);
                    players[playerTwoIndex].setGamePlayed(players[playerTwoIndex].getGamePlayed() + 1);
                    //start game
                    game.toReady();
                    boolean firstPlayer = game.startGame();// return result of the game
                    scanner.nextLine();
                    //add record according to the game result
                    if(firstPlayer){
                        players[playerOneIndex].setGameWon(players[playerOneIndex].getGameWon() + 1);
                    }
                    else {
                        players[playerTwoIndex].setGameWon(players[playerTwoIndex].getGameWon() + 1);
                    }
                }
                else{
                    System.out.println("One of the players does not exist.");
                }
            }
        }catch (IOException e){
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public void startAdvancedGame(String command){
        String[] gameParameter = command.split(",");
        try{
            if(gameParameter.length < 3){
                throw new IOException();
            }
            else{
                boolean playerOneExistence = false;
                boolean playerTwoExistence = false;
                int playerOneIndex = 0;
                int playerTwoIndex = 0;
                //search for both two players
                for(int round = 0;round < 99 && players[round] != null;round ++){
                    //player one exist
                    if(gameParameter[1].equals(players[round].getUsername())){
                        gameParameter[1] = players[round].getGivenname() + " " + players[round].getFamilyname();
                        playerOneExistence = true;
                        playerOneIndex = round;
                    }
                    //player two exist
                    if(gameParameter[2].equals(players[round].getUsername())){
                        gameParameter[2] = players[round].getGivenname() + " " + players[round].getFamilyname();
                        playerTwoExistence  = true;
                        playerTwoIndex = round;
                    }
                }
                //if both players exist
                if(playerOneExistence && playerTwoExistence){
                    int total = Integer.parseInt(gameParameter[0]);
                    //create object advancedGame, transfer essential parameters
                    AdvancedGame advancedGame = new AdvancedGame(total,players[playerOneIndex],players[playerTwoIndex]);
                    //change game status of two players
                    players[playerOneIndex].setGamePlayed(players[playerOneIndex].getGamePlayed() + 1);
                    players[playerTwoIndex].setGamePlayed(players[playerTwoIndex].getGamePlayed() + 1);
                    //start game
                    advancedGame.toReady();
                    boolean firstPlayer = advancedGame.startGame();// return result of the game
                    //add record according to the game result
                    if(!firstPlayer){
                        players[playerOneIndex].setGameWon(players[playerOneIndex].getGameWon() + 1);
                    }
                    else {
                        players[playerTwoIndex].setGameWon(players[playerTwoIndex].getGameWon() + 1);
                    }
                }
                else{
                    System.out.println("One of the players does not exist.");
                }
            }
        }catch (IOException e){
            System.out.println("Incorrect number of arguments supplied to command.");
        }
    }

    public void showTopTen(){
        for(int round = 0;round < 9 && players[round] != null;round ++){
            String percentage;
            //round the percentage
            percentage = String.valueOf(Math.round(players[round].getRatio()));
            System.out.print(percentage + "%");
            //control format of output
            for(int lenth = 4 - percentage.length();lenth > 0;lenth--){
                System.out.print(" ");
            }
            System.out.printf("| %02d",players[round].getGamePlayed());
            System.out.println(" games " + "| " + players[round].getFullName());
        }
    }

    public void rankings(String[] command){
        if(command.length == 1 || command[1].equals("desc")){//rank according to desc order
            //sort alphabetically first
            sortAlphabetically();
            //sort according to win ratio
            for(int loIndex = 0;loIndex < 99 && players[loIndex + 1] != null ; loIndex++){
                for(int hiIndex = loIndex + 1;hiIndex < 99 && players[hiIndex] != null ; hiIndex++)
                    if(players[loIndex].getRatio()<players[hiIndex].getRatio()){
                        NimPlayer temp;
                        temp = players[hiIndex];
                        players[hiIndex] = players[loIndex];
                        players[loIndex] = temp;
                    }
            }
            //display top 10
            showTopTen();
        }
        //asc
        else{
            //sort alphabetically first
            sortAlphabetically();
            //sort according to win ratio
            for(int loIndex = 0;loIndex < 99 && players[loIndex + 1] != null ; loIndex++){
                for(int hiIndex = loIndex + 1;hiIndex < 99 && players[hiIndex] != null ; hiIndex++)
                    if(players[loIndex].getRatio()>players[hiIndex].getRatio()){
                        NimPlayer temp;
                        temp = players[hiIndex];
                        players[hiIndex] = players[loIndex];
                        players[loIndex] = temp;
                    }
            }
            //display top 10
            showTopTen();
        }
    }

    public void start(NimGame play) throws IOException {
        play.welcome();
        readData();
        //judge the command and call correspond method
        while(true){
            String[] command = play.commandInput();
            try{
                if(command[0].equals("addplayer")){
                    play.addPlayer(command[1]);
                }
                else if(command[0].equals("addaiplayer")){
                    play.addAIPlayer(command[1]);
                }
                else if(command[0].equals("removeplayer")){
                    play.removePlayer(command);
                }
                else if(command[0].equals("startgame")){
                    play.startGame(command[1]);
                }
                else if(command[0].equals("editplayer")){
                    play.editPlayer(command[1]);
                }
                else if(command[0].equals("resetstats")){
                    play.resetStats(command);
                }
                else if(command[0].equals("displayplayer")){
                    play.displayPlayer(command);
                }
                else if(command[0].equals("rankings")){
                    play.rankings(command);
                }
                else if(command[0].equals("startadvancedgame")){
                    play.startAdvancedGame(command[1]);
                }
                else if(command[0].equals("exit")){
                    try {
                        //write data to players.dat
                        FileWriter myWriter = new FileWriter("players.dat");
                        for(int round = 0;round < 99 && players[round] != null;round ++) {
                            myWriter.write(String.valueOf(players[round] + "," + players[round].getPlayerType()) + "\n");
                        }
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("");
                    System.exit(0);
                }
                else{
                    throw new IOException();
                }
            } catch (IOException e){
                System.out.println("'" + command[0] + "'" +" is not a valid command.");
            }
        }
    }
}
