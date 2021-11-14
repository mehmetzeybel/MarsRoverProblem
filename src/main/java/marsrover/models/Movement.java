package marsrover.models;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mehmet on 11.11.2021.
 * Movement action capable of robot
 */
public enum Movement {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    public final Character description;

    Movement(Character description) {
        this.description=description;
    }

    public static Optional<Movement> findByDescription(Character description){
        return Arrays.stream(values()).filter(movement -> movement.description.equals(description)).findFirst();
    }
}
