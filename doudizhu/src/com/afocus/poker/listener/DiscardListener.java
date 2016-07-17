package com.afocus.poker.listener;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.rule.Rule;

import java.util.List;

/**
 * 输入响应（监听）
 */
public interface DiscardListener<T extends Rule> {

    boolean isSupport(List<Card> cards);

    public T parse(List<Card> cards);

    public void discard(T t);
}
