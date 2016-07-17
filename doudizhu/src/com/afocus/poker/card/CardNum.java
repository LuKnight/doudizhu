package com.afocus.poker.card;

/**
 * Created by Administrator on 2016/7/3.
 */
public enum CardNum {

    King(15,"大王"),SubKing(14,"小王"),TWO(13,"2"),ONE(12,"1"),K(11,"K"),
    Q(10,"Q"),J(9,"J"),TEN(8,"10"),NINE(7,"9"),EIGHT(6,"8"),SEVEN(5,"7"),
    SIX(4,"6"),FIVE(3,"5"),FOUR(2,"4"),THREE(1,"3");

    private int weight;

    private String name;

    CardNum(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
