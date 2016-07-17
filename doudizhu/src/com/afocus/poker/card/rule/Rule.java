package com.afocus.poker.card.rule;

import com.afocus.poker.card.Card;

import java.util.List;

/**
 * 规则基类，定义出牌的规则
 */
public abstract class Rule implements Comparable<Rule> {

    protected List<Card> cards;

    public Rule(List<Card> cards) {
        this.cards = cards;
    }

    public int getWeight(){
        return cards.get(0).getCardNum().getWeight();
    }

    public abstract boolean isComparable(Rule rule);

}
