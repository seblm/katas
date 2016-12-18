package name.lemerdy.sebastian;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;
import static java.util.OptionalInt.of;
import static name.lemerdy.sebastian.BowlingGame.MAX_PINS;

public class Frame {
    private final int score;
    private final boolean spare;
    private final boolean strike;

    private OptionalInt nextRoll = empty();
    private OptionalInt nextNextRoll = empty();

    private Frame(int firstRoll, int secondRoll) {
        this.score = firstRoll + secondRoll;
        this.spare = score == MAX_PINS;
        this.strike = false;
    }

    private Frame() {
        this.score = MAX_PINS;
        this.spare = false;
        this.strike = true;
    }

    public static Frame noStrike(int firstRoll, int secondRoll) {
        return new Frame(firstRoll, secondRoll);
    }

    public static Frame strike() {
        return new Frame();
    }

    public int score() {
        return score + spareBonus().orElse(0) + strikeBonus().orElse(0);
    }

    public void roll(int pins) {
        if (!nextRoll.isPresent()) {
            nextRoll = of(pins);
        } else if (!nextNextRoll.isPresent()) {
            nextNextRoll = of(pins);
        }
    }

    private OptionalInt spareBonus() {
        return spare ? nextRoll : empty();
    }

    private OptionalInt strikeBonus() {
        return strike ? of(nextRoll.orElse(0) + nextNextRoll.orElse(0)) : empty();
    }
}
