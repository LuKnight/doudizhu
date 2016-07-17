package com.afocus.poker.card.rule;

import com.afocus.poker.card.Card;

import java.util.List;

/**
 * 炸弹的规则
 */
public class BombRule extends Rule {

    public BombRule(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isComparable(Rule rule) {
        return true;
    }

    @Override
    public int compareTo(Rule o) {
        if(o instanceof BombRule){
            return this.getWeight() - o.getWeight();
        } else {
            return 1;
        }
    }
}
