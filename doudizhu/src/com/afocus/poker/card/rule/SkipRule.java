package com.afocus.poker.card.rule;

import com.afocus.poker.card.Card;

import java.util.List;

/**
 * 回合跳过的规则
 */
public class SkipRule extends Rule {

    public SkipRule(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isComparable(Rule rule) {
        return true;
    }

    @Override
    public int compareTo(Rule o) {
        return -1;
    }

    @Override
    public int getWeight() {
        return -1;
    }
}
