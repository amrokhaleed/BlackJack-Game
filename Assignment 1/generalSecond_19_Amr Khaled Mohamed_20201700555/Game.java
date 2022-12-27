package blackjack;
import java.util.Random;
import java.util.Scanner;


public class Game {
    public Card[] cards = new Card[52];
    public Player[] players = new Player[4];
    public int[] highScore = new int[4];

    public void generateCards(){
        int k = 0;
        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 13;j++){
                if (j < 9){
                    cards[k] = new Card(i,j,j+1);
                }
                else {
                    cards[k] = new Card(i,j,10);
                }
                k++;
            }   
        }
    }
    
    public Card draw(){
        Random ran = new Random();
        Card drawned;
        while(true){
            int randomCard = ran.nextInt(52);
            if (cards[randomCard] != null){
                drawned = new Card (cards[randomCard]);
                cards[randomCard] = null;
                break;
            }
        }
        return drawned;
    }
    
    public void setPlayersInfo(){
        Scanner in = new Scanner(System.in);
        for(int i = 0;i < players.length - 1;i++){
            System.out.println("Enter Player " + (i+1) + " name : ");
            players[i] = new Player();
            players[i].name = in.next();
            players[i].handCards(draw());
            players[i].handCards(draw());
        }
            players[3] = new Player();
            players[3].handCards(draw());
            players[3].handCards(draw());
    }
    public void setHighScores(){
        for(int i = 0;i < 4;i++){
            if(players[i].score <= 21){
                highScore[i] = players[i].score;
            }
            else{
                highScore[i] = 0;
            }
        }
    }
}
