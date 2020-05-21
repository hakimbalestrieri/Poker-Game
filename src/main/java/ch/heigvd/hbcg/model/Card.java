package ch.heigvd.hbcg.model;

import java.io.Serializable;

/**
 * classe Card, représente une carte
 * @authors Hakim Balestrieri, Christian Gomes
 */
public class Card implements Serializable {

    private Colors color;
    private Numbers number;

    /**
     * Constructeur
     * @param colors
     * @param numbers
     */
    public Card(Colors colors, Numbers numbers) {
        this.color = colors;
        this.number = numbers;
    }

    /**
     * Renvoie la couleur de la carte
     * @return Colors
     */
    public Colors getColor() {
        return color;
    }

    /**
     * Renvoie le numéro de la carte
     * @return Numbers
     */
    public Numbers getNumber() {
        return number;
    }

    /**
     * Spécialisation de l'affichage d'une carte
     * @return String
     */
    @Override
    public String toString() {
        return number + "_" + color;
    }
}
