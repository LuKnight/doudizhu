package com.afocus.poker.game;

import com.afocus.poker.card.rule.Rule;
import com.afocus.poker.card.rule.SkipRule;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏历史纪录，单例模式
 */
public class GameJudger {

    private static GameJudger ourInstance = new GameJudger();

    /**
     * 跳过次数计数
     */
    private int count = 0;

    public static GameJudger getInstance() {
        return ourInstance;
    }

    private GameJudger() {
    }

    private List<Rule> history = new ArrayList<>();


    public void setHistory(Rule rule){
        if(rule instanceof SkipRule){
            count++;
        }
        history.add(rule);
    }

    public Rule getLastRule(){
        if(history.size() == 0){
            return new SkipRule(null);
        }
        if(count == 1){
            return history.get(history.size()-2);
        } else {
            count = 0;
            return history.get(history.size()-1);
        }
    }
}
