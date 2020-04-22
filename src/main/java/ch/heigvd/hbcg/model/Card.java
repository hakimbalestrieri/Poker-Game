package ch.heigvd.hbcg.model;

/**
 * Class Card
 * Reprensent a simple card
 * @authors Balestrieri & Gomes
 */
public class Card {

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

}
