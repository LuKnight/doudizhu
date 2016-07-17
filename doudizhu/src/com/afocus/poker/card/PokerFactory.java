package com.afocus.poker.card;

import java.util.ArrayList;
import java.util.List;

/**
 * 扑克工厂，生成扑克(可以与工具类合并)
 */
public class PokerFactory {

    /**
     * 生成一套按大到小排列的扑克牌（包含大小王）
     * @return
     */
    public List<Card> getPoker(){
        List<Card> cards = new ArrayList<>();
        CardColor[] colors = CardColor.values();
        CardNum[] cardNums = CardNum.values();
        Card card = new Card();
        card.setCardNum(CardNum.King);
        cards.add(card);
        card = new Card();
        card.setCardNum(CardNum.SubKing);
        cards.add(card);
        for(int i = 2; i < cardNums.length ; i++){
            for(int j = 0 ; j < colors.length ; j++){
                card = new Card();
                card.setCardNum(cardNums[i]);
                card.setCardColor(colors[j]);
                cards.add(card);
            }
        }
        return cards;
    }

}
