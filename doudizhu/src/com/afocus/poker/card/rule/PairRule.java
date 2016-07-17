package com.afocus.poker.card.rule;

import com.afocus.poker.card.Card;

import java.util.List;

/**
 * 一对的规则
 */
public class PairRule extends Rule {

    public PairRule(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isComparable(Rule rule) {
        if(rule instanceof PairRule){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Rule o) {
        if(o instanceof PairRule){
            return this.getWeight() - o.getWeight();
        }
        throw new RuntimeException("两个规则不能比较");
    }
}
