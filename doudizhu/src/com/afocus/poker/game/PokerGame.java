package com.afocus.poker.game;

import com.afocus.poker.card.Card;
import com.afocus.poker.card.PokerFactory;
import com.afocus.poker.card.PokerUtil;
import com.afocus.poker.listener.BombDiscardListener;
import com.afocus.poker.listener.DiscardSink;
import com.afocus.poker.listener.PairDiscardListener;
import com.afocus.poker.listener.SkipRuleListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 斗地主游戏
 *
 */
public class PokerGame {

    private List<GameUser> users = new ArrayList<>();

    private int startIndex = -1;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private DiscardSink discardSink = new DiscardSink(new BombDiscardListener(),
            new SkipRuleListener(),
            new PairDiscardListener());

    public PokerGame() {
    }

    /**
     * 初始化扑克/玩家/玩家角色
     */
    private void init() {
        List<Card> cards = new PokerFactory().getPoker();
        LinkedList list1 = new LinkedList<Card>();
        LinkedList list2 = new LinkedList<Card>();
        LinkedList list3 = new LinkedList<Card>();
        int random = 0;
        while (cards.size() > 3) {
            random = Double.valueOf(Math.random() * cards.size()).intValue();
            list1.add(cards.remove(random));
            random = Double.valueOf(Math.random() * cards.size()).intValue();
            list2.add(cards.remove(random));
            random = Double.valueOf(Math.random() * cards.size()).intValue();
            list3.add(cards.remove(random));
        }
        GameUser user1 = new GameUser(list1);
        GameUser user2 = new GameUser(list2);
        GameUser user3 = new GameUser(list3);
        users.add(user1);
        users.add(user2);
        users.add(user3);


        //抢地主
        PokerUtil.sort(list1);
        PokerUtil.sort(list2);
        PokerUtil.sort(list3);
        for (GameUser user : users) {
            System.out.print("玩家" + (users.indexOf(user)+1)+"手牌"+ user.showHandCard() + "请输入(1.抢地主，其他.不抢):");
            String command = readUserCommand();
            if (command.equals("1")) {
                startIndex = users.indexOf(user);
            }
        }

        if (startIndex == -1) {
            System.out.print("没有地主，游戏结束");
        } else {
            users.get(startIndex).appendCards(cards);
            PokerUtil.sort(list1);
            PokerUtil.sort(list2);
            PokerUtil.sort(list3);
        }
    }

    /**
     * 游戏开始
     */
    public void start() {
        System.out.println("start");
        init();
        if (startIndex == -1) {
            return;
        }
        GameUser gameUser = users.get(startIndex%users.size());
        while (gameUser.getHandCardSize() != 0) {
            gameUser = users.get(startIndex%users.size());
            System.out.println("输入手牌id号打出手牌(打出多张逗号分隔,输入-1跳过回合)");
            System.out.println("玩家"+(startIndex%users.size()+1)+(gameUser.isPoor()?"（农民）手牌:":"（地主）手牌:"));
            System.out.println(gameUser.showHandCard());
            for(int i = 0 ; i < gameUser.getHandCardSize() ; i ++){
                System.out.print(i+",");
            }
            tryDiscard(gameUser);
            startIndex ++;
        }
        System.out.print("游戏结束"+(gameUser.isPoor()?"农民获胜":"地主获胜"));
        //TODO: 播放炫酷的音乐
        closeInput();
    }

    /**
     * 出牌
     * @param gameUser
     */
    private void tryDiscard(GameUser gameUser) {
        try{
            List<Card> inputCard = tryGetInput(gameUser);
            discardSink.handle(inputCard);
            gameUser.discard(inputCard);
        } catch (Exception e){
            System.out.print(e.getMessage()+",请重新出牌:");
            tryDiscard(gameUser);
        }

    }

    /**
     * 请求用户输入。输入非法，递归调用直到输入合法
     * @param gameUser
     * @return
     */
    private List<Card> tryGetInput(GameUser gameUser) {
        try {
            String command = readUserCommand();
            if("-1".equals(command)){
                return Collections.emptyList();
            }
            return gameUser.getInputCardByCommand(getIndex(command));
        } catch (Exception e){
            System.out.println(e.getMessage()+",请重新输入:");
            return tryGetInput(gameUser);
        }
    }

    /**
     * 读入用户输入
     * @return
     */
    public String readUserCommand() {
        String command = "";
        try {
            command = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return command;
    }

    /**
     * 关闭输入流
     */
    public void closeInput(){
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 命令转换成id数组
     * @param command
     * @return
     */
    private List<Integer> getIndex(String command){
        String[] commands = command.split(",");
        if(commands.length == 0){
            throw new RuntimeException("非法输入");
        }
        List<Integer> indexs = new ArrayList<>(commands.length);
        for(int i = 0 ; i < commands.length ; i ++){
            indexs.add(Integer.valueOf(commands[i]));
        }
        Collections.sort(indexs);
        return indexs;
    }
}
