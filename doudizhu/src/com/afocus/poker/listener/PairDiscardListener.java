package com.afocus.poker.listener;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.rule.PairRule;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class PairDiscardListener implements DiscardListener<PairRule>{

    @Override
    public PairRule parse(List<Card> cards) {
        return new PairRule(cards);
    }

    @Override
    public void discard(PairRule pairRule) {

    }

    @Override
    public boolean isSupport(List<Card> cards) {
        if(cards.size() == 2){
            if(cards.get(0).getCardNum().getWeight() == cards.get(1).getCardNum().getWeight()){
                return true;
            }
        }
        return false;
    }

}
