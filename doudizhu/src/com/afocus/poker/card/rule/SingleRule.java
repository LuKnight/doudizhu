package com.afocus.poker.card.rule;

import com.afocus.poker.card.Card;

import java.util.List;

/**
 * 出单张的规则
 */
public class SingleRule extends Rule {

    public SingleRule(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isComparable(Rule rule) {
        if(rule instanceof SingleRule){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Rule o) {
        if(o instanceof SingleRule){
            return getWeight() - o.getWeight();
        }
        throw new RuntimeException("与之前的输入不可比");
    }
}
