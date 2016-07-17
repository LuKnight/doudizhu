package com.afocus.poker.listener;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.rule.Rule;
import com.afocus.poker.card.rule.SkipRule;
import com.afocus.poker.game.GameJudger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 输入处理
 */
public class DiscardSink {

    private List<DiscardListener<? extends Rule>> listeners = new ArrayList<>();

    public DiscardSink(DiscardListener<? extends Rule>... listeners) {
        Collections.addAll(this.listeners,listeners);
    }

    private GameJudger gameJudger = GameJudger.getInstance();

    /**
     * 使用所有的响应方式处理用户输入，如果用户输入都没有对应的处理类，抛出异常给用户重新输入
     * @param cards
     */
    public void handle(List<Card> cards){
        Rule lastRule = gameJudger.getLastRule();
        boolean isHandler = false;
        for(DiscardListener<? extends Rule> listener : listeners){
            if(listener.isSupport(cards)){
                Rule rule = listener.parse(cards);
                if(lastRule instanceof SkipRule && cards.size() != 0){
                    //新一轮回合进入的分支
                    gameJudger.setHistory(rule);
//                    listener.discard(rule);
                    isHandler = true;
                    break;
                } else if(rule.compareTo(lastRule)>0 && rule.isComparable(lastRule)){
                    //有上下家的分支
                    gameJudger.setHistory(rule);
//                    listener.discard(rule);
                    isHandler = true;
                    break;
                } else if(!(lastRule instanceof SkipRule) && cards.size() == 0){
                    //跳过出牌回合的分支
                    gameJudger.setHistory(rule);
                    isHandler = true;
                }
            }
        }
        if(!isHandler){
            throw new RuntimeException("输入不符合规则");
        }
    }
}
