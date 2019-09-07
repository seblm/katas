package name.lemerdy.sebastian;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {
    private BowlingGame game;

    @BeforeEach
    void createBowlingGame() {
        game = new BowlingGame();
    }

    @Test
    void testGutterGame() {
        rollMany(20, 0);

        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    void testAllOnes() {
        rollMany(20, 1);

        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    void testOneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);

        assertThat(game.score()).isEqualTo(16);
    }

    @Test
    void testOneStrike() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);

        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    void testPerfectGame() {
        rollMany(14, 10);

        assertThat(game.score()).isEqualTo(300);
    }

    private void rollMany(int numberOfRolls, int pins) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(4);
        game.roll(6);
    }

    private void rollStrike() {
        game.roll(10);
    }
}