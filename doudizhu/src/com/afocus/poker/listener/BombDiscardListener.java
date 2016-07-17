package com.afocus.poker.listener;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.CardNum;
import com.afocus.poker.card.rule.BombRule;

import java.util.List;

/**
 */
public class BombDiscardListener implements DiscardListener<BombRule>{

    @Override
    public boolean isSupport(List<Card> cards) {
        if(cards.size() == 2){
            if(cards.get(0).getCardNum().getWeight()==CardNum.King.getWeight() &&
                    cards.get(1).getCardNum().getWeight()==CardNum.SubKing.getWeight()){
                //王炸
                return true;
            }
        }
        if(cards.size() == 4){
            if(cards.get(0).getCardNum().getWeight()==cards.get(1).getCardNum().getWeight() &&
                    cards.get(1).getCardNum().getWeight()==cards.get(2).getCardNum().getWeight()&&
                    cards.get(2).getCardNum().getWeight()==cards.get(3).getCardNum().getWeight()){
                //王炸以外的炸弹
                return true;
            }
        }
        return false;
    }

    @Override
    public BombRule parse(List<Card> cards) {
        return new BombRule(cards);
    }

    @Override
    public void discard(BombRule bombRule) {
    }
}
