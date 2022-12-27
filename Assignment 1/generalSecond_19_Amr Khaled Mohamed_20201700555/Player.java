package blackjack;



public class Player {
    private Card[] hCards = new Card[11];
    public String name;
    public int score = 0;
    private int cnt = 0;
    
    public void handCards(Card card){
        if (cnt < 11){
            hCards[cnt] = card;
            cnt++;
            score += card.getValue();
        }
    }
    public Card[] getHand(){
        return hCards;
    }
}
