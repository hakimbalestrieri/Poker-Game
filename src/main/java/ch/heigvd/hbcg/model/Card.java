package ch.heigvd.hbcg.model;

import java.io.Serializable;

/**
 * Class Card
 * Reprensent a simple card
 * @authors Balestrieri & Gomes
 */
public class Card implements Serializable {

    private Colors color;
    private Numbers number;

    public Card(Colors colors, Numbers numbers) {
        this.color = colors;
        this.number = numbers;
    }

    public Colors getColor() {
        return color;
    }

    public Numbers getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + "_" + color;
    }
}
