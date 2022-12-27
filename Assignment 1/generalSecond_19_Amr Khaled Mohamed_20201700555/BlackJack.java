package blackjack;
import java.util.Scanner;


public class BlackJack {

    static Game game = new Game();
    public static void main(String[] args) {
        GUI gui = new GUI();
        game.generateCards();
        game.setPlayersInfo();
        
        gui.runGUI(game.cards,game.players[0].getHand(),game.players[1].getHand(),game.players[2].getHand(),game.players[3].getHand());
        Turn(gui);
        
        game.setHighScores();
        dealerTurn(gui);
        game.setHighScores();
        checkWhoWin();
    }
    
    
    public static void Turn(GUI gui){
        Scanner input = new Scanner(System.in);
        
        for(int i = 0;i < game.players.length - 1;i++){
            String choice = "";
            while(!choice.equals("stand")){
                System.out.println("Player " + (i+1) + " Hit or Stand : ");
                choice = input.next();
                if(choice.equals("hit")){
                    addCardtoPlayer(i,gui); 
                }
                if (game.players[i].score > 21){
                    System.out.println("Busted");
                        break;
                }
            }
        }
    }
    
    public static void addCardtoPlayer(int i, GUI gui){
        Card card = game.draw();
        game.players[i].handCards(card);
        gui.updatePlayerHand(card, i);
    }
    
    
    public static void dealerTurn(GUI gui){
        boolean isWin = true;
        int hScore = 0;
        for(int i = 0;i < game.players.length - 1;i++){
            if (game.highScore[i] > game.players[3].score){
                isWin = false;
            }
            if(game.highScore[i] > hScore){
                hScore = game.highScore[i];
            }
        }
        if(!isWin){
            addCardToDealer(gui,hScore);
        }
    }
    public static void addCardToDealer(GUI gui,int hScore){
        while(game.players[3].score < hScore){
            Card card = game.draw();
            game.players[3].handCards(card);
            gui.updateDealerHand(card,game.cards);
        }
    }
    public static void checkWhoWin(){
        int hScore = 0;
        int win = -1;
        for(int i = 0;i < game.players.length;i++){
            if (game.highScore[i] > hScore){
                hScore = game.highScore[i];
                win = i;
            }
        }
        if (win >= 0){
            System.out.println("Winner is " + game.players[win].name + " with score " + hScore);
        }
        
    }
 }
