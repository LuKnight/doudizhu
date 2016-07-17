package com.afocus.poker.game;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.PokerUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class GameUser {

    private LinkedList<Card> handCards = new LinkedList<>();

    /**
     * 农民标识
     */
    private boolean isPoor = true;

    public GameUser(LinkedList<Card> handCards) {
        this.handCards = handCards;
    }

    /**
     *
     * @param inputCard
     * @return
     */
    public void discard(List<Card> inputCard){
        for(Card card : inputCard){
            handCards.remove(card);
        }
    }

    private List<Card> getInput(String[] commands) {
        List<Card> input = new ArrayList<>();
        for(int i = 0 ; i < commands.length ; i++){
            input.add(handCards.get(Integer.valueOf(commands[i])));
        }
        return input;
    }

    public boolean isPoor(){
        return isPoor;
    }

    public int getHandCardSize(){
        return handCards.size();
    }

    public void appendCards(List<Card> cards){
        handCards.addAll(cards);
        isPoor = false;
    }

    public List<Card> getInputCardByCommand(List<Integer> indexs){
        List<Card> cards = new ArrayList<>(indexs.size());
        for(Integer index : indexs){
            if(index > handCards.size()){
                throw new RuntimeException("输入超出id值");
            }
            cards.add(handCards.get(index));
        }
        return cards;
    }

    public String showHandCard() {
        return PokerUtil.showCard(handCards);
    }
}
