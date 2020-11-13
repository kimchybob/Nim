/**
 * @name : wenjie xu
 * @student number : 1039070
 * @username : wxxu1
 */
import java.io.IOException;

public class NimHumanPlayer extends NimPlayer {
    public NimHumanPlayer(String[] name) {
        super(name);
    }

    @Override
    public String advancedMove(boolean[] available, String lastMove) {
        String move = NimGame.scanner.nextLine();
        String[] remove = move.split(" ");
        int index ;
        int num ;
        try{
            //incorrect input
            if(remove.length != 2){
                throw  new IOException();
            }
            else{
                index = Integer.parseInt(remove[0]);
                num = Integer.parseInt(remove[1]);
                if((index+num-1) > available.length || num > 2 || !available[index - 1]){
                    throw  new IOException();
                }
                else if(num == 2 && !available[index]){
                    throw new IOException();
                }
            }

        }catch(IOException e){
            System.out.println("\nInvalid move.");
        }
        return move;
    }


//    //method to return how many stones player want to remove
//    public int removeStone(int remove,int total,int bound){
//        System.out.println("this is NimHumanPlayer removeStone");
//        try{
//            //incorrect input
//            if(remove < 1 || remove > total || remove > bound){
//                throw new IOException();
//            }
//        }catch(IOException e){
//            int min = total>bound?bound:total;
//            System.out.println("\nInvalid move. You must remove between 1 and " + min + " stones.");
//            remove = 0;
//        }
//        return remove;
//    }
}
