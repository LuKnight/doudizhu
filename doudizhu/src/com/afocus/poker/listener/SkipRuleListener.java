package com.afocus.poker.listener;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.rule.SkipRule;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class SkipRuleListener implements DiscardListener<SkipRule> {

    @Override
    public boolean isSupport(List<Card> cards) {
        if(cards == null || cards.size() == 0){
            return true;
        }
        return false;
    }

    @Override
    public SkipRule parse(List<Card> cards) {
        return new SkipRule(cards);
    }

    @Override
    public void discard(SkipRule skipRule) {

    }
}
