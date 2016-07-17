package com.afocus.poker.card;

import java.util.LinkedList;
import java.util.List;

/**
 * 工具类
 */
public class PokerUtil {

    /**
     * 排序扑克
     * @param cards
     */
    public static void sort(LinkedList<Card> cards){
        int maxIndex = -1;
        for(int i = 0 ; i < cards.size() ; i++){
            maxIndex = i;
            for(int j = i ; j <cards.size() ; j++){
                Card card2 = cards.get(j);
                if(card2.getCardNum().getWeight() > cards.get(maxIndex).getCardNum().getWeight()){
                    maxIndex = j;
                }
            }
            if(maxIndex != i){
                Card card1 = cards.get(i);
                cards.set(i,cards.get(maxIndex));
                cards.set(maxIndex,card1);
            }
        }
    }

    /**
     * 显示手牌
     * @param cards
     * @return
     */
    public static String showCard(List<Card> cards){
        String handCard = "";
        for(Card card : cards){
            handCard += card.toString()+",";
        }
        return handCard;
    }
}
