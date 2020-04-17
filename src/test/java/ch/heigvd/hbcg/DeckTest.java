package ch.heigvd.hbcg;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void drawCard() {
        assertEquals(deck.draw().getClass(), Card.class);
    }

    @Test
    void numberOfCards() {
        assertTrue(deck.size() == 52);
    }

    @Test
    void drawTwoCards(){
        deck.draw();
        deck.draw();
        assertTrue(deck.size() == 50);
    }

}