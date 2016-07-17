package com.afocus.poker.card;

import static java.lang.System.*;

/**
 * Created by Administrator on 2016/7/3.
 */
public class Card {

    private CardNum cardNum;

    private CardColor cardColor;

    public CardNum getCardNum() {
        return cardNum;
    }

    public void setCardNum(CardNum cardNum) {
        this.cardNum = cardNum;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public String toString() {
        return (cardColor == null?"":cardColor.toString())+cardNum.getName();
    }

    //out.println("akjdlf");
}
