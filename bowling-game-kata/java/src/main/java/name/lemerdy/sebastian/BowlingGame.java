package name.lemerdy.sebastian;

import java.util.LinkedHashSet;
import java.util.OptionalInt;
import java.util.Set;

import static java.util.OptionalInt.empty;
import static java.util.OptionalInt.of;
import static name.lemerdy.sebastian.Frame.noStrike;
import static name.lemerdy.sebastian.Frame.strike;

class BowlingGame {
    static final int MAX_PINS = 10;
    private static final int MAX_TURN = 10;

    private Set<Frame> frames = new LinkedHashSet<>();
    private OptionalInt previousPins = empty();

    void roll(int pins) {
        frames.forEach(frame -> frame.roll(pins));

        if (previousPins.isPresent()) {
            frames.add(noStrike(previousPins.getAsInt(), pins));
            previousPins = empty();
        } else if (pins == MAX_PINS) {
            frames.add(strike());
        } else {
            previousPins = of(pins);
        }
    }

    int score() {
        return frames.stream().limit(MAX_TURN).mapToInt(Frame::score).sum();
    }
}
